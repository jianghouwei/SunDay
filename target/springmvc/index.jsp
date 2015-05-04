<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/swfupload1/swfupload.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/swfupload1/handlers.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/swfupload1/fileprogress.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=request.getContextPath()%>/swfupload1/swfupload.queue.js"></script>

<script type="text/javascript">
		$(function(){
			var annex = new SWFUpload({
					upload_url: "http://localhost:8080/SunDay/file/upload",
					file_size_limit : "20 MB",	// 1000MB
					file_types : "*.*",//限制上传文件的类型。使用分号分隔各类型,"*.jpg;*.gif"表示只允许上传扩展名为JPG和GIF的文件
					file_types_description : "All Files",
					file_upload_limit : "0",//允许上传的文件数量。默认值为0，表示没有限制
					file_queue_limit : 0,
					
					// Event Handler Settings (all my handlers are in the Handler.js file)
					swfupload_preload_handler : preLoad,
					swfupload_load_failed_handler : loadFailed,
					file_dialog_start_handler : fileDialogStart,
					file_queued_handler : fileQueued,
					file_queue_error_handler : fileQueueError,
					file_dialog_complete_handler : fileDialogComplete,
					upload_start_handler : uploadStart,
					upload_progress_handler : uploadProgress,
					upload_error_handler : uploadError,
					upload_success_handler : uploadSuccess,
					upload_complete_handler : uploadComplete,

					// Button Settings
					button_image_url : "",
					button_placeholder_id : "spanButtonPlaceholder",
					button_width: 350,
					button_height: 18,
					
					button_text : '<span class="button">点击选择文件</span>',
					button_text_style :'',
					button_text_top_padding: 0,
					button_text_left_padding: 19,
					button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
					button_cursor: SWFUpload.CURSOR.HAND,
					requeue_on_error:false,//设置为true则当文件上传错误时重新返回队列等待上传
					// Flash Settings
					flash_url : "<%=request.getContextPath()%>/swfupload1/swfupload.swf",

					custom_settings : {
						progressTarget : "divFileProgressContainer",
						cancelButtonId : "btnUpload"
					},
					use_query_string : false,//要传递参数，必须配置,可以从后台取到参数,应该还有其他方式，如post方式，未了解 
					// Debug Settings
					debug : false
				//是否显示调试窗
				});
	});
</script>
</head>

<body>
	<table>
		<tr>
			<div
				style="width: 220px; height: 25px; line-height: 25px; padding-top: 7px; padding-right: 5px; padding-bottom: 5px; padding-left: 5px; margin-left: 50px; border-top-color: #7faaff; border-right-color: #7faaff; border-bottom-color: #7faaff; border-left-color: #7faaff; border-top-width: 1px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; border-top-style: solid; border-right-style: solid; border-bottom-style: solid; border-left-style: solid; display: inline; background-image: none; background-attachment: scroll; background-repeat: repeat; background-position-x: 0%; background-position-y: 0%; background-size: auto; background-origin: padding-box; background-clip: border-box; background-color: rgb(255, 255, 255);">
				<span id="spanButtonPlaceholder"></span> 
				<input id="btnUpload"type="button" value="上  传" style="height: 23px; display: none"
onclick="startUploadFile();" />
			</div>
			<br>
			<div id="divFileProgressContainer"
				style="border: none; margin-top: 8px;" value="dd"></div>
		</tr>
	</table>
</body>
</html>
