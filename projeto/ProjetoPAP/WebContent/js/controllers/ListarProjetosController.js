angular.module("app").controller('ListarProjetosCtrl', function($scope, $http, $stateParams, $cookieStore) {
	
	$scope.UsuarioLogado = $cookieStore.get("session_user_id");
	
	// Busca projetos geral
	$scope.BuscarInformacao = function() {

		$http.get('http://localhost:8080/ProjetoPAP/rest/projetorest')
				.success(function(data) {
					var projetosBanco = data["projeto"];
					var arrayBanco = [];
					if(Array.isArray(projetosBanco)){
						arrayBanco = projetosBanco; 
					}
					else{
						arrayBanco.push(projetosBanco);
					}
					$scope.projetos = arrayBanco;
				}).error(
						function(data, status, header, config) {
							swal("Não foi possivel listar, por favor tente novamente.");					
						});
	};
		
	// Busca projetos do usuário
	$scope.BuscarMeusProjetos = function() {
		$http.get('http://localhost:8080/ProjetoPAP/rest/projetorest/user/'+$scope.UsuarioLogado)
				.success(function(data) {
					var projetosBanco = data["projeto"];
					var arrayBanco = [];
					if(Array.isArray(projetosBanco)){
						arrayBanco = projetosBanco; 
					}
					else{
						arrayBanco.push(projetosBanco);
					}
					$scope.meusProjetos = arrayBanco;
					
				}).error(
						function(data, status, header, config) {
							swal("Não foi possivel listar, por favor tente novamente.");					
						});
	};
	
	$scope.BuscarInformacao();
	$scope.BuscarMeusProjetos();	
});


