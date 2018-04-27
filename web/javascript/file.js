$(document).ready(function()
{
    $("#submit").click(function(){
        if($('#searchLang').val() == '')
        {
            alert('Please write a language to search for!');
        }
        else
            {
               $('.pagi').css({"background-color":"white"});
               $('.pagi').css({"color":"black"});
               $('#pgOne').css({"background-color":"#9c27b0"});
               $('#pgOne').css({"color":"white"});
               $("#loader").fadeToggle(500);
               var language = $("#searchLang").val(); 
               //Make a request from github
               $.ajax({ url:'https://api.github.com/search/repositories?q=language:'+language+'&sort=stars&order=desc&page=1&per_page=30',
               }).done(function(final)
               {

                   $("#loader").hide();
                 //  $("body").css("background-image", "none");
                  // $('#form').css('background-image','url("css/bg5.jpg")');   
                   $('#repoInfo').empty();

                   for(i=0;i<30;i++)
                   {
                       $('#repoInfo').append(`

                    <div class="panel panel-default animated fadeIn">

                        <div class="panel-heading">
                            <span class="titles">Owner :</span>
                            <a href="${final.items[i].owner.html_url}">${final.items[i].owner.login}</a> &nbsp; 
                            <img src="${final.items[i].owner.avatar_url}" alt="Lights" class=" w3-circle w3-image w3-bar-item w3-mobile w3-card-4 ownerImg" style="width:100%;max-width:25px;">
                        </div>

                          <div class="panel-body"><span class="titles">Repository URL</span>: 
                            <a href="${final.items[i].html_url}">${final.items[i].html_url}</a><br> <span class="titles">Project description: </span>
                            ${final.items[i].description};
                        </div> 
                    </div>
                    <br>

                    `);
                   }
                    $('#pagination').css('visibility','visible');

               })
            }
 
    })
   
});
////////////
$(document).ready(function()
{
    $("#pgOne").click(function(){
        if($('#searchLang').val() == '')
        {
            alert('Please write a language to search for!');
        }
        else
            {
               $('.pagi').css({"background-color":"white"});
               $('.pagi').css({"color":"black"});
               $('#pgOne').css({"background-color":"#9c27b0"});
               $('#pgOne').css({"color":"white"});
               $("#loader").fadeToggle(500);
               var language = $("#searchLang").val(); 
               //Make a request from github
               $.ajax({ url:'https://api.github.com/search/repositories?q=language:'+language+'&sort=stars&order=desc&page=1&per_page=30',
               }).done(function(final)
               {

                   $("#loader").hide();
                 //  $("body").css("background-image", "none");
                  // $('#form').css('background-image','url("css/bg5.jpg")');   
                   $('#repoInfo').empty();

                   for(i=0;i<30;i++)
                   {
                       $('#repoInfo').append(`

                    <div class="panel panel-default animated fadeIn">

                        <div class="panel-heading">
                            <span class="titles">Owner :</span>
                            <a href="${final.items[i].owner.html_url}">${final.items[i].owner.login}</a> &nbsp; 
                            <img src="${final.items[i].owner.avatar_url}" alt="Lights" class=" w3-circle w3-image w3-bar-item w3-mobile w3-card-4 ownerImg" style="width:100%;max-width:25px;">
                        </div>

                          <div class="panel-body"><span class="titles">Repository URL</span>: 
                            <a href="${final.items[i].html_url}">${final.items[i].html_url}</a><br> <span class="titles">Project description: </span>
                            ${final.items[i].description};
                        </div> 
                    </div>
                    <br>

                    `);
                   }
                    $('#pagination').css('visibility','visible');

               })
            }
 
    })
   
});
////////////

$(document).ready(function()
{
    $("#pgTow").click(function(){
               $('.pagi').css({"background-color":"white"});
               $('.pagi').css({"color":"black"});
               $('#pgTow').css({"background-color":"#9c27b0"});
               $('#pgTow').css({"color":"white"});

               $("#loader").fadeToggle(500);
               var language = $("#searchLang").val(); 
               //Make a request from github
               $.ajax({ url:'https://api.github.com/search/repositories?q=language:'+language+'&sort=stars&order=desc&page=2&per_page=30',
               }).done(function(final)
               {

                   $("#loader").hide();
                  // $("body").css("background-image", "none");
                  //$('#form').css('background-image','url("css/bg5.jpg")');   
                   $('#repoInfo').empty();

                   for(i=0;i<30;i++)
                   {
                       $('#repoInfo').append(`

                    <div class="panel panel-default animated fadeIn">

                        <div class="panel-heading">
                            <span class="titles">Owner :</span>
                            <a href="${final.items[i].owner.html_url}">${final.items[i].owner.login}</a> &nbsp; 
                            <img src="${final.items[i].owner.avatar_url}" alt="Lights" class=" w3-circle w3-image w3-bar-item w3-mobile w3-card-4 ownerImg" style="width:100%;max-width:25px;">
                        </div>

                          <div class="panel-body"><span class="titles">Repository URL</span>: 
                            <a href="${final.items[i].html_url}">${final.items[i].html_url}</a><br> <span class="titles">Project description: </span>
                            ${final.items[i].description};
                        </div> 
                    </div>
                    <br>

                    `);
                   }
                    $('#pagination').css('visibility','visible');
               })
              
            }
 
    )
   
});
///////
$(document).ready(function()
{
    $("#pgThree").click(function(){
               $('.pagi').css({"background-color":"white"});
               $('.pagi').css({"color":"black"});
               $('#pgThree').css({"background-color":"#9c27b0"});
               $('#pgThree').css({"color":"white"});

               $("#loader").fadeToggle(500);
               var language = $("#searchLang").val(); 
               //Make a request from github
               $.ajax({ url:'https://api.github.com/search/repositories?q=language:'+language+'&sort=stars&order=desc&page=3&per_page=30',
               }).done(function(final)
               {

                   $("#loader").hide();
                  // $("body").css("background-image", "none");
                   //$('#form').css('background-image','url("css/bg5.jpg")');   
                   $('#repoInfo').empty();

                   for(i=0;i<30;i++)
                   {
                       $('#repoInfo').append(`

                    <div class="panel panel-default animated fadeIn">

                        <div class="panel-heading">
                            <span class="titles">Owner :</span>
                            <a href="${final.items[i].owner.html_url}">${final.items[i].owner.login}</a> &nbsp; 
                            <img src="${final.items[i].owner.avatar_url}" alt="Lights" class=" w3-circle w3-image w3-bar-item w3-mobile w3-card-4 ownerImg" style="width:100%;max-width:25px;">
                        </div>

                          <div class="panel-body"><span class="titles">Repository URL</span>: 
                            <a href="${final.items[i].html_url}">${final.items[i].html_url}</a><br> <span class="titles">Project description: </span>
                            ${final.items[i].description};
                        </div> 
                    </div>
                    <br>

                    `);
                   }

               })
            }
 
    )
   
});
//////
$(document).ready(function()
{
    $("#pgFour").click(function(){
               $('.pagi').css({"background-color":"white"});
               $('.pagi').css({"color":"black"});
               $('#pgFour').css({"background-color":"#9c27b0"});
               $('#pgFour').css({"color":"white"});

               $("#loader").fadeToggle(500);
               var language = $("#searchLang").val(); 
               //Make a request from github
               $.ajax({ url:'https://api.github.com/search/repositories?q=language:'+language+'&sort=stars&order=desc&page=4&per_page=30',
               }).done(function(final)
               {

                   $("#loader").hide();
                   $("body").css("background-image", "none");
                   //$('#form').css('background-image','url("css/bg5.jpg")');   
                   //$('#repoInfo').empty();

                   for(i=0;i<30;i++)
                   {
                       $('#repoInfo').append(`

                    <div class="panel panel-default animated fadeIn">

                        <div class="panel-heading">
                            <span class="titles">Owner :</span>
                            <a href="${final.items[i].owner.html_url}">${final.items[i].owner.login}</a> &nbsp; 
                            <img src="${final.items[i].owner.avatar_url}" alt="Lights" class=" w3-circle w3-image w3-bar-item w3-mobile w3-card-4 ownerImg" style="width:100%;max-width:25px;">
                        </div>

                          <div class="panel-body"><span class="titles">Repository URL</span>: 
                            <a href="${final.items[i].html_url}">${final.items[i].html_url}</a><br> <span class="titles">Project description: </span>
                            ${final.items[i].description};
                        </div> 
                    </div>
                    <br>

                    `);
                   }

               })
            }
 
    )
   
});
////
$(document).ready(function()
{
    $("#pgFive").click(function(){
               $('.pagi').css({"background-color":"white"});
               $('.pagi').css({"color":"black"});
               $('#pgFive').css({"background-color":"#9c27b0"});
               $('#pgFive').css({"color":"white"});

               $("#loader").fadeToggle(500);
               var language = $("#searchLang").val(); 
               //Make a request from github
               $.ajax({ url:'https://api.github.com/search/repositories?q=language:'+language+'&sort=stars&order=desc&page=5&per_page=30',
               }).done(function(final)
               {

                   $("#loader").hide();
                   //$("body").css("background-image", "none");
                   //$('#form').css('background-image','url("css/bg5.jpg")');   
                   $('#repoInfo').empty();

                   for(i=0;i<30;i++)
                   {
                       $('#repoInfo').append(`

                    <div class="panel panel-default animated fadeIn">

                        <div class="panel-heading">
                            <span class="titles">Owner :</span>
                            <a href="${final.items[i].owner.html_url}">${final.items[i].owner.login}</a> &nbsp; 
                            <img src="${final.items[i].owner.avatar_url}" alt="Lights" class=" w3-circle w3-image w3-bar-item w3-mobile w3-card-4 ownerImg" style="width:100%;max-width:25px;">
                        </div>

                          <div class="panel-body"><span class="titles">Repository URL</span>: 
                            <a href="${final.items[i].html_url}">${final.items[i].html_url}</a><br> <span class="titles">Project description: </span>
                            ${final.items[i].description};
                        </div> 
                    </div>
                    <br>

                    `);
                   }

               })
            }
 
    )
   
});

////
$(document).ready(function()
{
    $("#pgSix").click(function(){
               $('.pagi').css({"background-color":"white"});
               $('.pagi').css({"color":"black"});
               $('#pgSix').css({"background-color":"#9c27b0"});
               $('#pgSix').css({"color":"white"});

               $("#loader").fadeToggle(500);
               var language = $("#searchLang").val(); 
               //Make a request from github
               $.ajax({ url:'https://api.github.com/search/repositories?q=language:'+language+'&sort=stars&order=desc&page=6&per_page=30',
               }).done(function(final)
               {

                   $("#loader").hide();
                   //$("body").css("background-image", "none");
                   //$('#form').css('background-image','url("css/bg5.jpg")');   
                   $('#repoInfo').empty();

                   for(i=0;i<30;i++)
                   {
                       $('#repoInfo').append(`

                    <div class="panel panel-default animated fadeIn">

                        <div class="panel-heading">
                            <span class="titles">Owner :</span>
                            <a href="${final.items[i].owner.html_url}">${final.items[i].owner.login}</a> &nbsp; 
                            <img src="${final.items[i].owner.avatar_url}" alt="Lights" class=" w3-circle w3-image w3-bar-item w3-mobile w3-card-4 ownerImg" style="width:100%;max-width:25px;">
                        </div>

                          <div class="panel-body"><span class="titles">Repository URL</span>: 
                            <a href="${final.items[i].html_url}">${final.items[i].html_url}</a><br> <span class="titles">Project description: </span>
                            ${final.items[i].description};
                        </div> 
                    </div>
                    <br>

                    `);
                   }

               })
            }
 
    )
   
});
////
$(document).ready(function()
{
    $("#pgSeven").click(function(){
               $('.pagi').css({"background-color":"white"});
               $('.pagi').css({"color":"black"});
               $('#pgSeven').css({"background-color":"#9c27b0"});
               $('#pgSeven').css({"color":"white"});

               $("#loader").fadeToggle(500);
               var language = $("#searchLang").val(); 
               //Make a request from github
               $.ajax({ url:'https://api.github.com/search/repositories?q=language:'+language+'&sort=stars&order=desc&page=7&per_page=30',
               }).done(function(final)
               {

                   $("#loader").hide();
                   //$("body").css("background-image", "none");
                   //$('#form').css('background-image','url("css/bg5.jpg")');   
                   $('#repoInfo').empty();

                   for(i=0;i<30;i++)
                   {
                       $('#repoInfo').append(`

                    <div class="panel panel-default animated fadeIn">

                        <div class="panel-heading">
                            <span class="titles">Owner :</span>
                            <a href="${final.items[i].owner.html_url}">${final.items[i].owner.login}</a> &nbsp; 
                            <img src="${final.items[i].owner.avatar_url}" alt="Lights" class=" w3-circle w3-image w3-bar-item w3-mobile w3-card-4 ownerImg" style="width:100%;max-width:25px;">
                        </div>

                          <div class="panel-body"><span class="titles">Repository URL</span>: 
                            <a href="${final.items[i].html_url}">${final.items[i].html_url}</a><br> <span class="titles">Project description: </span>
                            ${final.items[i].description};
                        </div> 
                    </div>
                    <br>

                    `);
                   }

               })
            }
 
    )
   
});
////
$(document).ready(function()
{
    $("#pgEight").click(function(){
               $('.pagi').css({"color":"black"});
               $('.pagi').css({"background-color":"white"});
               $('#pgEight').css({"background-color":"#9c27b0"});
               $('#pgEight').css({"color":"white"});

               $("#loader").fadeToggle(500);
               var language = $("#searchLang").val(); 
               //Make a request from github
               $.ajax({ url:'https://api.github.com/search/repositories?q=language:'+language+'&sort=stars&order=desc&page=8&per_page=30',
               }).done(function(final)
               {

                   $("#loader").hide();
                   //$("body").css("background-image", "none");
                   //$('#form').css('background-image','url("css/bg5.jpg")');   
                   $('#repoInfo').empty();

                   for(i=0;i<30;i++)
                   {
                       $('#repoInfo').append(`

                    <div class="panel panel-default animated fadeIn">

                        <div class="panel-heading">
                            <span class="titles">Owner :</span>
                            <a href="${final.items[i].owner.html_url}">${final.items[i].owner.login}</a> &nbsp; 
                            <img src="${final.items[i].owner.avatar_url}" alt="Lights" class=" w3-circle w3-image w3-bar-item w3-mobile w3-card-4 ownerImg" style="width:100%;max-width:25px;">
                        </div>

                          <div class="panel-body"><span class="titles">Repository URL</span>: 
                            <a href="${final.items[i].html_url}">${final.items[i].html_url}</a><br> <span class="titles">Project description: </span>
                            ${final.items[i].description};
                        </div> 
                    </div>
                    <br>

                    `);
                   }

               })
            }
 
    )
   
});
////
$(document).ready(function()
{
    $("#pgNine").click(function(){
               $('.pagi').css({"color":"black"});
               $('.pagi').css({"background-color":"white"});
               $('#pgNine').css({"background-color":"#9c27b0"});
               $('#pgNine').css({"color":"white"});

               $("#loader").fadeToggle(500);
               var language = $("#searchLang").val(); 
               //Make a request from github
               $.ajax({ url:'https://api.github.com/search/repositories?q=language:'+language+'&sort=stars&order=desc&page=9&per_page=30',
               }).done(function(final)
               {

                   $("#loader").hide();
                   //$("body").css("background-image", "none");
                   //$('#form').css('background-image','url("css/bg5.jpg")');   
                   $('#repoInfo').empty();

                   for(i=0;i<30;i++)
                   {
                       $('#repoInfo').append(`

                    <div class="panel panel-default animated fadeIn">

                        <div class="panel-heading">
                            <span class="titles">Owner :</span>
                            <a href="${final.items[i].owner.html_url}">${final.items[i].owner.login}</a> &nbsp; 
                            <img src="${final.items[i].owner.avatar_url}" alt="Lights" class=" w3-circle w3-image w3-bar-item w3-mobile w3-card-4 ownerImg" style="width:100%;max-width:25px;">
                        </div>

                          <div class="panel-body"><span class="titles">Repository URL</span>: 
                            <a href="${final.items[i].html_url}">${final.items[i].html_url}</a><br> <span class="titles">Project description: </span>
                            ${final.items[i].description};
                        </div> 
                    </div>
                    <br>

                    `);
                   }

               })
            }
 
    )
   
});
////
$(document).ready(function()
{
    $("#pgTen").click(function(){
               $('.pagi').css({"color":"black"});
               $('.pagi').css({"background-color":"white"});
               $('#pgTen').css({"background-color":"#9c27b0"});
               $('#pgTen').css({"color":"white"});

               $("#loader").fadeToggle(500);
               var language = $("#searchLang").val(); 
               //Make a request from github
               $.ajax({ url:'https://api.github.com/search/repositories?q=language:'+language+'&sort=stars&order=desc&page=10&per_page=30',
               }).done(function(final)
               {

                   $("#loader").hide();
                   //$("body").css("background-image", "none");
                   //$('#form').css('background-image','url("css/bg5.jpg")');   
                   $('#repoInfo').empty();

                   for(i=0;i<30;i++)
                   {
                       $('#repoInfo').append(`

                    <div class="panel panel-default animated fadeIn">

                        <div class="panel-heading">
                            <span class="titles">Owner :</span>
                            <a href="${final.items[i].owner.html_url}">${final.items[i].owner.login}</a> &nbsp; 
                            <img src="${final.items[i].owner.avatar_url}" alt="Lights" class=" w3-circle w3-image w3-bar-item w3-mobile w3-card-4 ownerImg" style="width:100%;max-width:25px;">
                        </div>

                          <div class="panel-body"><span class="titles">Repository URL</span>: 
                            <a href="${final.items[i].html_url}">${final.items[i].html_url}</a><br> <span class="titles">Project description: </span>
                            ${final.items[i].description};
                        </div> 
                    </div>
                    <br>

                    `);
                   }

               })
            }
 
    )
   
});