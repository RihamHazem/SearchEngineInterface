<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>particles.js</title>
  <link rel="icon" href="images/icon2.png">
  <link rel="stylesheet" href="css/animate.css">
  <script src="javascript/jquery-1.11.3.min.js"></script>
  <script src="javascript/file.js"></script>
  <script src="javascript/plugins.js"></script>
  <script src="javascript/file.js"></script>
  <script type="text/javascript"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
  <meta name="author" content="Vincent Garreau" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <link rel="stylesheet" media="screen" href="css/s.css">
</head>
<body>


<!-- particles.js container -->
<div id="particles-js">
<div id="login">
  <form class="w3-center" action="#" method="post" id="form">
    <h1 class="w3-wide  w3-center sw3-mobile animated bounceIn" id="intro"> Search the Web!</h1>
    <div id="image">
      <img src="images/icon1.png">
    </div>
    <div class=" w3-container w3-center" id="data-list">
        <input class="w3-input w3-round-large w3-mobile animated bounceInRight" autocomplete="off" style="float: left; width: 65%;" align="middle"name="searchQuery" id="searchQuery" type="search" placeholder="Search...">
        <button type="submit" class="w3-button w3-round-large w3-mobile animated bounceInLeft submitSearch" style="float: right;" id="btn"> Submit</button>

        <div id="myDropdown" class="dropdown-content">
        </div>
    </div>
    <div id="loader"></div>
  </form>
</div>
</div>
<span id="doYouMean"></span><a id="search-result"></a>


<!-- scripts -->
<script src="javascript/particles.js"></script>
<script src="javascript/app.js"></script>

</body>
</html>