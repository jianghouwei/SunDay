<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../swfupload/handlers.js" type="text/javascript"></script>
<script src="../swfupload/jquery-1.4.1.min.js" type="text/javascript"></script>
<script src="../swfupload/swfupload.js" type="text/javascript"></script>
<script src="../swfupload/fileprogress.js" type="text/javascript"></script>
<script src="../swfupload/swfupload.queue.js" type="text/javascript"></script>
<script type="text/javascript">
	var swfu;
	window.onload = function() {
		swfu = new SWFUpload(
				{
					// Backend Settings
					//修改执行上传操作的文件(aspx或ashx)
					upload_url : "http://localhost:8080/Sunday/index",
					// File Upload Settings
					file_size_limit : "2 MB",
					file_types : "*.jpg",
					file_types_description : "JPG Images",
					file_upload_limit : 0, // Zero means unlimited

					//swfupload_preload_handler : preLoad,
					swfupload_load_failed_handler : loadFailed,
					file_queue_error_handler : fileQueueError,
					file_dialog_complete_handler : fileDialogComplete,
					upload_progress_handler : uploadProgress,
					upload_error_handler : uploadError, 
					//指定图片上传成功后执行的方法为我们自己定义的ShowData
					upload_success_handler : ShowData,
					upload_complete_handler : uploadComplete,

					// Button settings
					button_image_url : "images/XPButtonNoText_160x22.png",
					button_placeholder_id : "spanButtonPlaceholder",
					button_width : 160,
					button_height : 22,
					button_text : '<span class="button">Select Images <span class="buttonSmall">(2 MB Max)</span></span>',
					button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 14pt; } .buttonSmall { font-size: 10pt; }',
					button_text_top_padding : 1,
					button_text_left_padding : 5,

					// Flash Settings
					//在这里修改flash插件的引用路径
					flash_url : "/swfupload/swfupload.swf", // Relative to this file
					flash9_url : "/swfupload/swfupload_FP9.swf", // Relative to this file

					custom_settings : {
						upload_target : "divFileProgressContainer"
					},

					// Debug Settings
					debug : false
				});
	}
	//上传成功后执行,将img标签的src设置为返回的图片保存好的路径
	function ShowData(file, serverData) {
		$("#imgSrc").attr("src", serverData);
	};
</script>
</head>
<body>
	<div id="content">
		<div id="swfu_container" style="margin: 0px 10px;">
			<div>
				<span id="spanButtonPlaceholder"></span>
			</div>
			<div id="divFileProgressContainer" style="height: 75px;"></div>
			<div id="thumbnails"></div>
		</div>
	</div>
	<img id="imgSrc" />
</body>
</html>