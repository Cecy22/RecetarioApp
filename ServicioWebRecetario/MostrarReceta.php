<?php
//recibiendo usu y pass

$Nom=$_REQUEST['nom'];
if(!isset($Nom)){
	$Nom="";
}
$R['estado']="OK";
	try{
		$conn = new PDO("mysql:host=localhost;dbname=recetario", "root", "root");
		$conn->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
		$sql=$conn->prepare("CALL spMosReceta(:Nombre)");
		$sql->execute(array('Nombre'=>$Nom));
		$R['fila']=$sql->rowcount();
		if($sql->rowcount()>0){
			$R['datos']=$sql->fetchAll();
		}
		$conn=null;
	}catch(PDOEXEPTION $e){
		$R['estado']="ERROR: ".$e->getMessage();
	}
echo json_encode($R['datos']);
?>