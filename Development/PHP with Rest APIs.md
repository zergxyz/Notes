Following is a sample code to send a Php Curl request to Rest API of Parse.  Replace the placeholders with your Application ID and Rest API Key in the code and run it from a php server.
``` php
 <?php  
 $url = 'https://api.parse.com/1/classes/GameScore';  
 $appId = 'YOUR_APP_ID';  
 $restKey = 'YOUR_REST_KEY';  
 $headers = array(  
   "Content-Type: application/json",  
   "X-Parse-Application-Id: " . $appId,  
   "X-Parse-REST-API-Key: " . $restKey  
 );  
 $objectData = '{"name":"Adarsh", "age":"26"}';  
 $rest = curl_init();  
 curl_setopt($rest,CURLOPT_URL,$url);  
 curl_setopt($rest,CURLOPT_POST,1);  
 curl_setopt($rest,CURLOPT_POSTFIELDS,$objectData);  
 curl_setopt($rest,CURLOPT_HTTPHEADER,$headers);  
 curl_setopt($rest,CURLOPT_SSL_VERIFYPEER, false);  
 curl_setopt($rest,CURLOPT_RETURNTRANSFER, true);  
 $response = curl_exec($rest);  
 echo $response;  
 print_r($response);  
 curl_close($rest);  
 ?>  

This is the good way to use php and curl lib to make restful web service operation. Similar case is used in certain project and code can be viewed in get and put methods:

PHP PUT
<?php

$appId = 'AZiWWcqR5aWK2BKBUV82fLp8gizYQVrIAXzSLA3z';
$restKey = 'Qk9ZLHUEiXMALqH7AkfIxOIBfie1WFsM8vkifvRh';
$headers = array(
"Content-Type: application/json",
"X-Parse-Application-Id: " . $appId,
"X-Parse-REST-API-Key: " . $restKey
);
$obID = 'SvZDG4ZJS5';
$url = "https://api.parse.com/1/classes/Note/".$obID;
$ctns= '{"contents" : "1234567"}';

$rest = curl_init();
curl_setopt($rest,CURLOPT_URL,$url);
curl_setopt($rest,CURLOPT_HTTPHEADER,$headers);
curl_setopt($rest,CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($rest, CURLOPT_RETURNTRANSFER, true);
curl_setopt($rest, CURLOPT_CUSTOMREQUEST, "PUT");
curl_setopt($rest, CURLOPT_POSTFIELDS,$ctns);


$response = curl_exec($rest);
echo $response;
//print_r($response);
curl_close($rest);
?>

 PHP get

$url = "https://api.parse.com/1/classes/Note";
$appId = 'AZiWWcqR5aWK2BKBUV82fLp8gizYQVrIAXzSLA3z';
$restKey = 'Qk9ZLHUEiXMALqH7AkfIxOIBfie1WFsM8vkifvRh';
$headers = array(
"Content-Type: application/json",
"X-Parse-Application-Id: " . $appId,
"X-Parse-REST-API-Key: " . $restKey
);
$filter = 'Acute Abdomen';
$query = urlencode('where={"cardName":"'.$filter.'"}');
$rest = curl_init("https://api.parse.com/1/classes/Note?".$query);
//curl_setopt($rest,CURLOPT_URL,$url);
//curl_setopt($rest,CURLOPT_POST,1);
//curl_setopt($rest,CURLOPT_GET,1);
//curl_setopt($rest,CURLOPT_POSTFIELDS,$objectData);
curl_setopt($rest,CURLOPT_HTTPHEADER,$headers);
curl_setopt($rest,CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($rest,CURLOPT_RETURNTRANSFER, true);
$response = curl_exec($rest);
echo $response;
curl_close($rest);
?>
```


how to use php to set up google smtp gmail without auth failure errors? 
https://serverfault.com/questions/635139/how-to-fix-send-mail-authorization-failed-534-5-7-14

