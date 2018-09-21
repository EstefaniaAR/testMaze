var app = angular.module('app', []);

app.controller('MainController', ['$scope','$http','$location', function($scope,$http,$location) 
{
	var host =  $location.host();
	var port = ':'+$location.port();
	var endpointGet = '//create_maze';

	
	$scope.getMaze = function()
	{
		var resource = 'http://localhost:8080/create_maze';
		console.log(resource);
		$http.get(resource)
			.then(function successCallback(response) 
			{
			    $scope.maze = response.data;
			}, function errorCallback(response) 
			{
			    alert("Error:"+JSON.stringify(response.data));
			    console.log(response);
			});
	};
}]);

