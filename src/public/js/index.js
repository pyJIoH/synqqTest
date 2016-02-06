(function() {
	var app = angular.module('app', []).controller('ctrl',
			function($scope, $http) {
				$scope.start = function() {
					$http({
						method : 'GET',
						url : '/data'
					}).then(function successCallback(response) {
						alert(response.data);
					}, function errorCallback(response) {
					});
				}

				$scope.stop = function() {
				}
			});
})()