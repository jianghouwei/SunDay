<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片上传</title>
</head>
<script type="text/javascript" charset="utf-8" src="../js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" charset="utf-8" src="../swfupload1/swfupload.js"></script> 
<script type="text/javascript" charset="utf-8" src="../swfupload1/handlers.js"></script> 
<script type="text/javascript" charset="utf-8" src="../swfupload1/fileprogress.js"></script> 
<script type="text/javascript" charset="utf-8" src="../swfupload1/swfupload.queue.js"></script> 
<script type="text/javascript" charset="utf-8" src="../swfupload1/jdMarquee.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	initUploadControl();
	scollUploadImg();
});
//滚动图片
function scollUploadImg() {
	jQuery("#scollUp").unbind('click');
	jQuery("#scollDown").unbind('click');
	var list = jQuery("ul[name=mscolluploadImg-list]");
	if (list.length == 2) {
		list.eq(1).remove();
	}
	$(".mscoll").jdMarquee({
		deriction: "left",
		//auto:true,
		width: 300,
		height: 52,
		step: 1,
		speed: 5,
		delay: 10,
		control: true,
		_front: "#scollDown",
		_back: "#scollUp"
	});
	//jQuery(".img-list li").Jdropdown(); 
}
var swfu;
//上传图片
function initUploadControl() {
	var url = "http://localhost:8080/SunDay/file/upload";
	var urlswf = "<%=request.getContextPath()%>/swfupload1/swfupload.swf";
	var urlswf_9 = "<%=request.getContextPath()%>/swfupload1/swfupload_fp9.swf";
	var settings = {
		flash_url : urlswf,
		flash9_url : urlswf_9,
		upload_url: url,
		post_params: {"upload": "", "op": "applyUpload"},
		file_size_limit: "5120 KB",
		file_types: "*.bmp;*.gif;*.jpg;*.png;*.jpeg",
		file_types_description: "*.bmp;*.gif;*.jpg;*.png;*.jpeg",
		file_upload_limit: 5,
		file_queue_limit: 1,
		custom_settings: {
			cancelButtonId: "btnCancel"
		},
		debug: false,
		button_width: "75",
		button_height: "25",
		button_text : '<span class="button">点击选择文件</span>',
		button_placeholder_id: "spanButtonPlaceHolder",
		file_dialog_start_handler: fileDialogStart,
		file_queued_handler: fileQueued,
		file_queue_error_handler: fileQueueError,
		file_dialog_complete_handler: fileDialogComplete,
		upload_start_handler: function () {
			/* alert(c.uploadImgs.length);
			var imgSize = c.uploadImgs.length;
			if (imgSize == 5) {
				showInputTips('mscolluploadImg-info', '抱歉，您上传的图片数已达总数5张,不能再继续上传了。', 'btnCancel', 'button');
				swfu.cancelUpload(undefined, false);
			} */
		},
		upload_progress_handler: function () {
			/* showInputTips('mscolluploadImg-info', c.loading + '图片上传中...', 'btnCancel', 'button'); */
		},
		upload_error_handler: function (e) {
			/* showInputTips('mscolluploadImg-info', '上传图片网络异常,请稍后重试.', 'btnCancel', 'button'); */
		},
		upload_success_handler: function (f, d, r) {
			//d = eval('(' + d + ')');
			if (d != null) {
				var scollupViewState = jQuery("div[name='mscolluploadImg']").css('display');
				if (scollupViewState == 'none') {
					jQuery("div[name='mscolluploadImg']").show();
				}
				//c.uploadImgs.push(d.optDescription);
				//c.uploadImgs.reverse();
				//jQuery("ul[name='mscolluploadImg-list']").empty();
				//for (var i = 0; i < c.uploadImgs.length; i++) {
					//var img = c.uploadImgs[i];
					/* var imgSrc = 'http://img30.360buyimg.com/myjd/' + img;
					var thumbnail = 'http://img30.360buyimg.com/asf/s60x60_' + img; */
					img = d ;
					var imgSrc ='http://localhost:8080/SunDay/upload/'+ d;
					var thumbnail ='http://localhost:8080/SunDay/upload/'+d;
					alert(imgSrc);
					alert(thumbnail);
					var listSize = jQuery("ul[name=mscolluploadImg-list] li").length;
					var li_name = "mscolluploadImg-list-li_" + (listSize + 1);
					var li = "<li name='" + li_name + "'><a href=\"" + imgSrc + "\" target=\"_blank\"><img class=\"err-product\" width=\"50\" height=\"50\" src=\"" + thumbnail + "\" data-img='1'></a><b onclick=\"delApplyUploadImg('" + img + "')\">&times;</b></li>";
					jQuery("ul[name='mscolluploadImg-list']").prepend(li);
				//}
				///scollUploadImg();
			} else {
				showInputTips('mscolluploadImg-info', '网络异常,上传失败,请稍后重试,谢谢合作!', 'btnCancel', 'button');
			}
		},
		upload_complete_handler: function (f) {
		},
		button_action: SWFUpload.BUTTON_ACTION.SELECT_FILE
	};
	swfu = new SWFUpload(settings);
}
//删除图片
function delApplyUploadImg(imgSrc) {
	if (confirm('您确定要删除该张图片吗?')) {
		//交换数组
		var tempArray = [];
		for (var i = 0; i < c.uploadImgs.length; i++) {
			var img = c.uploadImgs[i];
			if (img != imgSrc) {
				tempArray.push(img);
			}
		}
		c.uploadImgs = tempArray;
		jQuery("ul[name='mscolluploadImg-list']").empty();
		for (var i = 0; i < c.uploadImgs.length; i++) {
			var img = c.uploadImgs[i];
			var imgSrc = 'http://img30.360buyimg.com/myjd/' + img;
			var thumbnail = 'http://img30.360buyimg.com/asf/s60x60_' + img;
			var listSize = jQuery("ul[name=mscolluploadImg-list] li").length;
			var li_name = "mscolluploadImg-list-li_" + (listSize + 1);
			var li = "<li name='" + li_name + "'><a href=\"" + imgSrc + "\" target=\"_blank\"><img class=\"err-product\" width=\"50\" height=\"50\" src=\"" + thumbnail + "\" data-img='1'></a><b onclick=\"delApplyUploadImg('" + img + "')\">&times;</b></li>";
			jQuery("ul[name='mscolluploadImg-list']").prepend(li);
			scollUploadImg();
		}
		if (c.uploadImgs.length > 0) {
			jQuery("div[name='mscolluploadImg']").show();
		} else {
			jQuery("div[name='mscolluploadImg']").hide();
		}
	}
}
</script>
<body>
<div class="item item01">
	<span class="label">图片信息：</span>
	<div class="fl">
		<div class="mscoll" name="mscolluploadImg" style="display: none;">
			<div class="mleft" id="scollUp">上一个</div>
			<div class="mright" id="scollDown">下一个</div>
			<div class="mslist">
				<ul class="img-list" name="mscolluploadImg-list" style="width: 60px; overflow: hidden;"></ul>
			</div>
		</div>
		<div class="btns01">
		    <span id="spanButtonPlaceHolder"></span>
		    <input type="hidden" id="btnCancel">
		</div>
		<span id="uploadImgLoading" class="fl"></span>
		<span id="img_des" class="fl">为了帮助我们更好的解决问题，请您上传图片</span>
		<div class="clr"></div>
		<span class="msg-text">最多可上传5张图片，每张图片大小不超过5M，支持bmp,gif,jpg,png,jpeg格式文件</span>

		<div class="clr"></div>
		<div name="mscolluploadImg-info" style="display: none;" class="msg-error-01"><img name="loading" src="/misc/img/loading.gif" "="">图片上传中...</div>
	</div>
	<div class="clr"></div>
</div>
</body>
</html>