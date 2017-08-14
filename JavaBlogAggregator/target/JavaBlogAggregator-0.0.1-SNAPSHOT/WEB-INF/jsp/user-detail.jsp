<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>


<h1>${user.name}</h1>


<br/><br/>

<script type="text/javascript">
$(document).ready(function(){
	$('.nav-tabs a:first').tab('show'); // Select first tab
	$(".triggerRemove").click(function(e) {
		e.preventDefault();
		$("#modalRemove .removeButton").attr("href", $(this).attr("href"));
		$("#modalRemove").modal();
	});

});
</script>

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
  <c:forEach items="${user.blogs}" var="blog">
      <li ><a href="#blog_${blog.id}"  data-toggle="tab">${blog.name}</a></li>
  
  </c:forEach>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <c:forEach items="${user.blogs}" var="blog">
  
    <div class="tab-pane" id="blog_${blog.id}">
   <h1>${blog.name}</h1>
	<p>
	<a href='<spring:url value="/blog/remove/${blog.id}.html"></spring:url>' class="btn btn-danger triggerRemove">remove blog</a>
	
	${blog.url}</p>

	<table class="table table-bordered table-striped table-hover">
		<thead>
			<tr>
				<th>Title</th>
				<th>Link</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${blog.items}" var="item">
				<tr>
					<td>${item.title}</td>
					<td>${item.link}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</c:forEach>
</div>


<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove blog</h4>
      </div>
      <div class="modal-body">
        Are you shore about removing the blog?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <a href="" class="btn btn-danger removeButton">Remove</a>
      </div>
    </div>
  </div>
</div>





	
