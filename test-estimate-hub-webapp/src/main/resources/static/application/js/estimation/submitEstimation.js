var submitEstimation=(function() {
	var xpaths={
		"startDate" : "input#startDate",
		"endDate" : "input#endDate",
		"calculateEstimationButton" : "button#calculateEstimationButton",
		"calculateEstimationForm" : "form#calculateEstimationForm"
	};
	
	var calculateEstimation=function(event) {
		event.preventDefault();
		console.debug("Calculating estimation");
		if($(xpaths["calculateEstimationForm"]).validate()) {
			console.debug($(xpaths["calculateEstimationForm"]).serializeObject());
		}
	}
	
	var init=function() {
		console.debug("Initialized Estimation Submission!!!");
		$(xpaths["startDate"]).datepicker();
		$(xpaths["endDate"]).datepicker();
		$(xpaths["startDate"]).change(updateChangeEndDateMinValue);
		$(xpaths["calculateEstimationForm"]).submit(calculateEstimation);
	};
	
	var updateChangeEndDateMinValue=function(event) {
		event.preventDefault();
		var startDateSelected=$(xpaths["startDate"]).val().split("/");
		var formattedStartDate=new Date(startDateSelected[2],startDateSelected[0]-1,startDateSelected[1]);
		console.debug();
		$(xpaths["endDate"]).val("");
		$(xpaths["endDate"]).datepicker("destroy");
		$(xpaths["endDate"]).datepicker({
			minDate: formattedStartDate
		});
	};
	
	return {
		init : init
	}
})();