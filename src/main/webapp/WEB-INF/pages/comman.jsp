<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty successMessage or not empty errorMessage}">
<div style="padding-left: 187px;padding-right: 187px;">
			
		
				<c:if test="${not empty successMessage}">
					<div class="alert alert-success alert-dismissable">
						<strong>Success!</strong> ${successMessage}. <span
							class="closebtn pull-right"
							onclick="this.parentElement.style.display='none';"><i
							class="fa fa-times"></i></span>
					</div>

				</c:if>
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-danger alert-dismissable">
						<strong>Error!</strong> ${errorMessage}. <span class="closebtn pull-right"
							onclick="this.parentElement.style.display='none';"><i
							class="fa fa-times"></i></span>
					</div>
				</c:if>
</div>
</c:if>
<script>
	$(document).ready(function() {
			setTimeout(function() {
			$(".alert-success").alert('close');
		}, 2000);
	});
</script>