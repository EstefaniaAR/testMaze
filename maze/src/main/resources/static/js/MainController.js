var app = angular.module('app', []);

app.controller('MainController', ['$scope','$http','$location', function($scope,$http,$location) 
{
	$scope.render = false;
	$scope.getMaze = function()
	{
		var resource = 'http://localhost:8080/create_maze';
		console.log(resource);
		$http.get(resource)
			.then(function successCallback(response) 
			{
			    $scope.maze = response.data;
			    $scope.render = true;
			}, function errorCallback(response) 
			{
			    alert("Error:"+JSON.stringify(response.data));
			    console.log(response);
			});
	};
	
	$scope.move = function(dir)
	{
		var resource = 'http://localhost:8080/move';
		$scope.maze.move = dir;
		$http.post(resource,$scope.maze)
		.then(function successCallback(response) 
		{
			$scope.maze= response.data;
			console.log(response);
		}, function errorCallback(response) 
		{
		    alert("Error:"+JSON.stringify(response.data));
		    console.log(response);
		});
	};
	
	
}]);

