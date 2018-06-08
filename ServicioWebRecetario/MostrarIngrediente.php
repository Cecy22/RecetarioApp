<?php
//recibiendo usu y pass
//spMosIngrediente`(in pidr int)
$Idr=$_REQUEST['idr'];

$R['estado']="OK";
	try{
		$conn = new PDO("mysql:host=localhost;dbname=recetario", "root", "root");
		$conn->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
		$sql=$conn->prepare("CALL spMosIngrediente(:Idr)");
		$sql->execute(array('Idr'=>$Idr));
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