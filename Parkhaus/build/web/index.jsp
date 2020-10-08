<html>
    <head>
        <title>Parkhaus</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="content-type" content="text/html; charset=windows-1250">
        <link rel="stylesheet" type="text/css" href="style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js" type="text/javascript"></script> 
        <script src="script.js" type="text/javascript" charset="UTF-8"></script>                       
    </head>
    
    <body onLoad="initialize(), switchToUser(false)">        
        <div id="main">
            <div id="header">                
                <h1>PARKHAUS-SIMULATOR</h1>
            </div>
            
            <div id="content"></div>
            
            <div id="footer">
                <button class="button" onclick="switchUser()">BENUTZER WECHSELN</button>
            </div>           
        </div>
    </body>
</html>