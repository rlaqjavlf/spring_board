<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Read Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="" role="form">
                				<div class="form-group">
                					<label>Bno</label>
                					<input class="form-control" name="bno" readonly="readonly" value="${vo.bno}">                				
                				</div> 
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title" readonly="readonly" value="${vo.title}">                				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content" readonly="readonly" >${vo.content}</textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer" readonly="readonly" value="${vo.writer}">                				
                				</div>  
                				<button type="button" class="btn btn-default">글 수정</button>     			
                				<button id="listBtn" type="button" class="btn btn-info">게시판 보기</button>          			
                			</form>
                		</div>
                	</div>
                </div>
            </div> 
            
                 
            <div class='bigPictureWrapper'>
           <div class='bigPicture'>
           </div>
         </div>
            
            <link rel="stylesheet" href="/resources/dist/css/mycss.css"/>
            
            <div class="row">
               <div class="col-lg-12">
                  <div class="panel panel-default">
                     <div class="panel-heading">Files</div>
                        <div class="panel-body">
                           <div class="uploadResult">
                              <ul></ul>
                           </div>
                        </div>
                  </div>
               </div>            
            </div>
  <!-- 댓글 영역 -->
  <div class="row">
               <div class="col-lg-12">
                  <div class="panel panel-default">
                     <div class="panel-heading">
                        <i class="fa fa-comments fa-fw"></i>                  
                        Reply
                        <button id='addReplyBtn' class='btn btn-primary btn-xs pull-right' data-toggle="modal">New Reply</button>
                     </div><!-- ./ end panel-heading  --> 
                     <div class="panel-body">
                        <ul class="chat">
                           <!--  start reply -->
                           <li class="left clearfix" data-rno='12'>
                              <div>
                                 <div class="header">
                                    <strong class="primary-font">user00</strong>
                                    <small class="pull-right text-muted">2018-11-06 10:10</small>
                                 </div>
                                 <p>Good Job!!!</p>
                              </div>                              
                           </li>
                        </ul>                     
                     </div><!-- ./ end panel-body  --> 
                     <div class="panel-footer"> <!-- 댓글 페이지 영역 -->
                     			
                     </div>
                  </div>
               </div>
            </div> 
 
   <!--댓글등록 modal-->         
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel">Reply Modal</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                               <label>Reply</label>
                               <input class="form-control" name="reply" value="New Reply">
                            </div>
                            <div class="form-group">
                               <label>Replyer</label>
                               <input class="form-control" name="replyer" value="replyer">
                            </div>
                            <div class="form-group">
                               <label>Reply Date</label>
                               <input class="form-control" name="replyDate" value="">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" id="modalModBtn">Modify</button>
                            <button type="button" class="btn btn-danger" id="modalRemoveBtn">Remove</button>
                            <button type="button" class="btn btn-primary" id="modalRegisterBtn">Register</button>              
                            <button type="button" class="btn btn-success" id="modalCloseBtn">Close</button>                    
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal-dialog -->
            </div>
            <!-- /.modal -->                  
<form action="/board/modify" id="operForm">
 	<input type="hidden" name="bno" value="${vo.bno }"/>
 	<input type="hidden" name="pageNum" value="${cri.pageNum }"/>
 	<input type="hidden" name="amount" value="${cri.amount }"/>
 	<input type="hidden" name="type" value="${cri.type }"/>
	<input type="hidden" name="keyword" value="${cri.keyword }"/>
</form> 

<script>
$(function () {
	$.getJSON("/board/getAttachList"),{bno:bno},function(arr){
		console.log(arr);
	}
	
})
</script>

<script src="/resources/js/reply.js"></script>
 <script>
 	$(function() {
////////현재 글번호 가져오기
 		var bno=${vo.bno};
 		
 		//전체 댓글 목록을 보여중 화면 영역을 가저온다
 		var replyUL=$(".chat");
 		//showList(1);
 		var pageNum=1;
 		//댓글 페이징
 		var replyPageFooter=$(".panel-footer");
 		
////////현재 글번호 페이지 나누기
 		function showReplyPage(replyCnt){
 				var endNum=Math.ceil(pageNum/10.0)*10;
 				var startNum = endNum-9;
 				var prev = startNum != 1;
 				var next=false;
 				
 				if(endNum*10 >= replyCnt){
 					endNum=Math.ceil(replyCnt/10.0)
 				}
 				
 				if (endNum*10<replyCnt){
 					next=true;
 				}
 				var str="<ul class='pagination pull-right''>";
 				
 				if(prev){
 					str+="<li class = 'page-item'><a class ='page-link' href='";
 					str+=(startNum-1)+"'>Previous</a></li>"
 				}
 				
 			for(var i = startNum ; i<=endNum; i++){
 				var active = pageNum==i?"active" :"";
 				str+="<li class ='page-item "+active+"'>";
 				str+="<a class ='page-link' href='"+i+"'>"+i+"</a></li>";
 			}
 			if(next){
					str+="<li class = 'page-item'><a class ='page-link' href='";
					str+=(endNum+1)+"'>Next</a></li>"
				}	
 				str+="</ul></div>";
 				replyPageFooter.html(str);
 		}
 		//위임
 		$(replyPageFooter).on("click","li a",function(e){
 			e.preventDefault();
 			//사용자가 누른 번호 가져오기 
 			pageNum=$(this).attr("href");
 			showList(pageNum);
 		})
 		showList(1);
 		
 		
 		//모달처리
 		var modal=$(".modal");
 		var modalInputReply = modal.find("input[name='reply']")
 		var modalInputReplyer = modal.find("input[name='replyer']")
 		var modalInputReplyDate = modal.find("input[name='replyDate']")
 		
 		var modalModBtn=$("#modalModBtn");
 		var modalRemoveBtn=$("#modalRemoveBtn");
 		var modalRegisterBtn=$("#modalRegisterBtn");
 		
////////close 버튼
 		$("#modalCloseBtn").click(function () {
			modal.modal("hide");
		})
 		
 		$("#addReplyBtn").click(function () {
 			//input 태그 안에 들어있는 모든 값 초기화
				modal.find("input").val(""); 			
			//date 가 들어가 있는 div 영역안보이게 하기
				modalInputReplyDate.closest("div").hide();
			//close 버튼만 제외하고 전부 안보이게 만들기 
				modal.find("button[id!='modalCloseBtn']").hide();
			//등록버튼만 ㄱ다시보이게하기
				modalRegisterBtn.show();
			
 			$(".modal").modal("show");
 		})

/////////댓글 등록작업
 			//{"bno": 7736,"reply": "댓글작성한다","replyer": "test123"} 		
 		$(modalRegisterBtn).click(function(){
 			var reply={
 					bno : bno,
 					reply : modalInputReply.val(),
 					replyer : modalInputReplyer.val()
 			};
	 		replyService.add(reply,
	 						function(result) {
						//alert("result : " +result);
						//modal 창에 쓴내용 초기화
						modal.find("input").val("");
						modal.modal("hide");
						//댓글갱신
						showList(1)
					});
 			
 		})
		
////////댓글 리스트
		function showList(page){
		replyService.getList({bno:bno,page:page || 1},
			function(replyCnt,list){
				//alert("data : "+data);
				console.log("list : "+list.length);
				
				//페이지가 안넘어오는경우
				if (page==-1){
					pageNum = Math.ceil(replyCnt/10.0);
					showList(pageNum);
					return;
				}
				
				if(list == null || list.length==0){
					replyUL.html("");
					return;
				}
				//댓글 목록이 있는경우 
				var str="";
				for (var i=0,len=list.length||0;i<len;i++){
					str+=" <li class='left clearfix' data-rno='"+list[i].rno+"'>";
					str+=" <div class='header'><strong class='primary-font'>";
					str+=list[i].replyer+"</strong>";
					str+="<small class='pull-right text-muted'>"+replyService.displayTime(list[i].replyDate)+"</small>";
					str+=" </div><p>"+list[i].reply+"</p></div></li>";
				}
				replyUL.html(str);
				//페이징 처리 함수 호출
				showReplyPage(replyCnt);
			})	;
		}//showList 끝
		
				
		$(modalModBtn).click(function(){
/////////댓글 수정(/replies/3+put(patch))
			var reply={
					rno:modal.data("rno"),
					reply:modalInputReply.val()
			};		
			
			replyService.update(reply,function (result) {
					//console.log(result)
					//수정이 성공되면 창을 닫아준다
					modal.modal("hide");
					//다시 댓글 리스트 가져와서 뿌림
					showList(pageNum);
			});
			
		})
		
//////// 댓글 삭제
		$(modalRemoveBtn).click(function(){
	
			replyService.remove(modal.data("rno"),function(result){
				//console.log(result);
				modal.modal("hide");
				showList(pageNum);
			},function(err){
				alert('에러발생');	
			
		})
		})
		
///////이벤트 위임 방식 현재 존재하는 이벤트에 이벤트를 걸고 나중에 변경하는방식
		$(".chat").on("click","li",function(){
			var rno = $(this).data("rno");		
///////////댓글 하나 가져오기
			replyService.get(rno,function(data){
				//console.log(data);
				//모달창에 정보 뿌려주기
				modalInputReply.val(data.reply);
				modalInputReplyer.val(data.replyer);
				modalInputReplyDate.val(replyService.displayTime(data.replyDate));
				//]읽기 전용으로 바꾸기
				modalInputReplyDate.attr("readonly","true");
				//rno을 계속 사용해야되기 때문에 modal에 담아준다
				modal.data("rno",data.rno);
				
				modal.find("button[id!='modalCloseBtn']").hide();
				modalModBtn.show();
				modalRemoveBtn.show();
				modal.modal("show")
			})
		})
		
			
	})
 
 </script>
      
      
      
      
      
            <!-- 스크립트 -->
            <script>
            	$(function(){
            		var form =$("#operForm");
            		
            		$("#listBtn").click(function () {
        				//location.href="/board/list";
        				//operForm 보내기 (페이지 정보가 들어있는)
        				//bno 없애주기
        				form.find("input[name='bno']").remove();
        				form.attr("action","/board/list");
        				form.submit();	
        			});
            		
        			$(".btn-default").click(function(){
        				form.submit();
        			});
            	})
            </script> 
            
                
<%@include file="../includes/footer.jsp" %>       