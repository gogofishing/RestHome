this.lostFocusGlobal = function(thisId){
	var thisVal = $(thisId).val();
	if(thisVal!=""){
		$(thisId).css("backgroundColor","rgb(255,255,255)");
	}else{
		$(thisId).css("backgroundColor","#E6E1E1");
	}
};