<?php
//recibiendo usu y pass
$usu=$_REQUEST['usu'];
$pass=$_REQUEST['pass'];

$R['estado']="OK";
try{
	$conn = new PDO("mysql:host=localhost;dbname=recetario", "root", "root");
	$conn->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION);
	$sql=$conn->prepare("CALL spLogin(:Usu,:Contra)");
	$sql->execute(array('Usu'=>$usu, 'Contra'=>$pass));
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