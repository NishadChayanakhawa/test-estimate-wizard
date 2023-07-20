var submitEstimation=(function() {
	var xpaths={
		"requirementsAccordian" : "div#requirementsAccordian",
		"addUseCaseButtons" : "button[id^='addUseCaseButton_']",
		"useCaseFormTemplate" : "#useCaseFormTemplate",
		"deleteUseCaseButtons" : "button[id^='deleteUseCaseButton_']"
	};
	
	var addUseCase=function(event) {
		event.preventDefault();
		var requirementId=$(this).attr('id').split("_")[1];
		var currentUseCaseCount=parseInt($("input#useCaseCount_" + requirementId).val());
		var changeId=$("input#useCaseChangeID_" + requirementId + '_' + currentUseCaseCount).val();
		
		console.debug("Adding use case for Change# " + changeId);
		console.debug("Adding use case for Requirement# " + requirementId);
		console.debug("Current Use Case Count : " + currentUseCaseCount);
		
		var addUseCaseData={
			'changeId' : changeId,
			'requirementId' : requirementId,
			'id' : (currentUseCaseCount + 1)
		}
		
		$(xpaths["useCaseFormTemplate"]).tmpl(addUseCaseData).appendTo($("div#useCaseForms_" + requirementId));
		$("input#useCaseCount_" + requirementId).val(currentUseCaseCount+1);
	};
	
	var deleteUseCase=function(event) {
		event.preventDefault();
		var requirementId=$(this).attr('id').split("_")[1];
		var useCaseId=$(this).attr('id').split("_")[2];
		console.debug("Requirement: " + requirementId + " Use Case: " + useCaseId);
		$("form#useCaseForm_" + requirementId + "_" + useCaseId).remove();
	}
	
	var init=function() {
		console.debug("Initialized Estimation Submission!!!");
		
		$(xpaths["requirementsAccordian"]).on("click",xpaths["addUseCaseButtons"],addUseCase);
		$(xpaths["requirementsAccordian"]).on("click",xpaths["deleteUseCaseButtons"],deleteUseCase);
	};
	
	return {
		init : init
	}
})();