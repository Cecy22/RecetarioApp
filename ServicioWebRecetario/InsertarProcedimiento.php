<?php
//Recibe datos
//spInsProcedimiento`(in pdes varchar(400), pnom varchar(45), pidr int

$des=$_REQUEST['des'];

if ( isset($des) ){
	$R['estado']="OK";
	try{
		$conn = new PDO("mysql:host=localhost;dbname=recetario", "root", "root");
		$conn->setAttribute(PDO::ATTR_ERRMODE,PDO::ERRMODE_EXCEPTION); 
		$sql=$conn->prepare("CALL spInsProcedimiento( :Des)");
		$sql->execute(array( 'Des'=>$des));
		$conn=null;
		echo json_encode($R);
	}catch(PDOEXEPTION $e){
		$R['estado']="Error".$e->getMessage();
		echo json_encode($R);
	}	
}else{
	$R['estado']="NO";
	echo json_encode($R);
}
?>