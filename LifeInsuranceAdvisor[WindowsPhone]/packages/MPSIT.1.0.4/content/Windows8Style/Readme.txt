JavaScript library provides a simple and easy to use template to build tile base windows 8 look and feel web pages.
Build your own template and pass it to this library in order to convert your website to a tile based website (tiles, groups, live tiles, animation, ...).

Building a one tile and one live tile page is as simple as this (please use jQuery 1.7.1 only)

--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------
    <!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
        <title>Untitled 1</title>
    
        <script type="text/javascript" src='http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.1.min.js'></script>
        <script type="text/javascript" src='windows8StyleJS.js'></script>
        <link href="windows8StyleCSS.css" rel="stylesheet" />
    
        <script type="text/javascript">
            $(document).ready(function () {
                applyWindows8Style();
            });
        </script>
    
    </head>
    
    <body>
        <div data-type="windows8style" data-title="Page Name">
            <div data-type="group" data-title="Group One">
                <div data-type="tile" data-title="title 1" data-subtitle="subtitle" data-link="http://www.bing.com" data-backgroundcolor="white"></div>
            </div>
    		<div data-type="group" data-title="Group Two">
                <div data-type="livetile" data-title="title 1" data-subtitle="subtitle" data-link="http://www.bing.com">
    				<div data-type="tile" data-backgroundcolor="gray"></div>
    				<div data-type="tile" data-backgroundcolor="white"></div>
    			</div>
            </div>
        </div>
    </body>
    </html>
--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------

in general the template contains the following 
    a 'div' with a windows8style data-type; this div takes the following data annotation attributes
        title (string)
        back (url)
        titlefixedlocation (true/false ... default true)
        - this div contains one or more group; see the following point
    a 'div' with group data-type; this div takes the following data annotation attributes
        title (string)
        width (int)
        link (url)
        -this div contains one or more tile/livetile; see the following point
    a 'div' with either tile or livetile data-type; this div takes the following data annotation attributes
        title (string)
        subtitle (string)
        backgroundcolor (color)
        link (url)
        size (small/medium/large/wide ... default large)
        width (int)
        height (int)
        image (url ... specific for tile divs only)
        - a live tile should contain one or more tile on it, also a tile can be empty or it can contain any custom html controls
also you need to call the applyWindow8Style function in order to apply your template to your page, this function takes the following parameters
    selector: jQuery selector where you need to apply your template on (default  $('body'))
    animation: apply page load animation (default true)
    scrolling: enable horizontal page scrolling (default true)
you may use the function updateLiveTile in order to inject real live tile on fly
you also can change the colors, fonts,... by replacing/modifying windows8StyleCSS.css file

Please take a look at the example provided for more help.

