var isAdmin = false;

var parkingSpaces;
var occupiedCount;

const ParkingSpaceState = {
    Empty:0,
    Occupied:1,
    Payed:2    
};

const parkingSpaceCount = 16;

var parkingSpaceStates = new Array(parkingSpaceCount).fill(ParkingSpaceState.Empty);

function initialize()
{
    callFunction("Initialize", parkingSpaceCount + "");
}

function switchUser()
{
    switchToUser(!isAdmin);    
}

function switchToUser(type)
{
    isAdmin = type;   

    if (isAdmin)
    {
        $('#content').load("admin.jsp", function() {
            loadTicketData();
        });
    }
    else
    {
        $('#content').load("fahrer.jsp", function() {
            updateParkingSpace();
        });
    }
}

function tryEnter(parkingSpace)
{    
    if (!parkingSpace.classList.contains('occupied') && !parkingSpace.classList.contains('payed'))
        enter(parkingSpace);
}

function enter(parkingSpace)
{   
    parkingSpace.classList.add('occupied');

    setOccupiedCount(occupiedCount + 1);
    parkingSpaceStates[parkingSpace.id] = ParkingSpaceState.Occupied;
    
    callFunction("Enter", parkingSpace.id + "");
}

function tryPay(parkingSpace)
{
    if (parkingSpace.classList.contains('occupied'))
        pay(parkingSpace);
}

function pay(parkingSpace)
{
    parkingSpace.classList.replace('occupied', 'payed');
    parkingSpaceStates[parkingSpace.id] = ParkingSpaceState.Payed;
    callFunction("Pay", parkingSpace.id + "");
}

function tryLeave(parkingSpace)
{  
    if (parkingSpace.classList.contains('payed'))
        leave(parkingSpace);
}

function leave(parkingSpace)
{
    parkingSpace.classList.remove('payed');    
    setOccupiedCount(occupiedCount - 1);
    
    callFunction("Leave", parkingSpace.id + "");

    parkingSpaceStates[parkingSpace.id] = ParkingSpaceState.Empty;
}

function setOccupiedCount(count)
{
    occupiedCount = count;
    updateOccupiedCount();
}

function updateOccupiedCount()
{
    document.getElementById("freeSpaces").innerHTML = parkingSpaces.length - occupiedCount + " FREI";    
    
    if (occupiedCount === parkingSpaces.length)
        document.getElementById("freeSpaces").classList.add('occupied');
    else
        document.getElementById("freeSpaces").classList.remove('occupied');
}

function updateParkingSpace()
{
    parkingSpaces = new Array(parkingSpaceCount);   
    
    occupiedCount = 0;
    
    var rows = $(".parkingSpace_row");

    $.get("parkingSpace.html", function(response) 
    {
        for (var i = 0; i < rows.length; i++) 
        {
            rows[i].innerHTML = "";
            var div = rows.eq(i); 
            for (var j = 0; j < parkingSpaceCount / rows.length; j++)
                div.append($(response));
        }
        
        parkingSpaces = document.getElementsByClassName("parkingSpace"); 
        
        occupiedCount = 0;

        for (var i = 0; i < parkingSpaces.length; i++) 
        {
            parkingSpaces[i].id = i;

            if (parkingSpaceStates[i] === ParkingSpaceState.Occupied)
            {
                parkingSpaces[i].classList.add('occupied');
                occupiedCount++;
            }
            else if (parkingSpaceStates[i] === ParkingSpaceState.Payed)
            {
                parkingSpaces[i].classList.add('payed');
                occupiedCount++;
            }
        }    

        updateOccupiedCount();
    });    
}

function loadTicketData()
{
    callFunction("UpdateTicketList", "", "updateTicketList");
    callFunction("GetEarnings", "", "getEarnings");
}

function callFunction(func, param = "", resultfunc = "")
{   
    jQuery(function() 
    {        
        $.ajax({
            url : 'ParkhausServlet',
            type: 'POST',
            data : 
            {
                func: func,
                param: param
            },
            success : function(data) 
            {
                if (resultfunc !== "")
                    window[resultfunc](data);
            }
        });
    });   
}

function updateTicketList(param)
{
    document.getElementById("data").innerHTML = param;
}

function getEarnings(param)
{
    document.getElementById("earnings").innerHTML = "Gesamteinnahmen: " + param;
}