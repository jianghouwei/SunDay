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
					file_types : "*.*",//�����ϴ��ļ������͡�ʹ�÷ֺŷָ�������,"*.jpg;*.gif"��ʾֻ�����ϴ���չ��ΪJPG��GIF���ļ�
					file_types_description : "All Files",
					file_upload_limit : "0",//�����ϴ����ļ�������Ĭ��ֵΪ0����ʾû������
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
					
					button_text : '<span class="button">���ѡ���ļ�</span>',
					button_text_style :'',
					button_text_top_padding: 0,
					button_text_left_padding: 19,
					button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
					button_cursor: SWFUpload.CURSOR.HAND,
					requeue_on_error:false,//����Ϊtrue���ļ��ϴ�����ʱ���·��ض��еȴ��ϴ�
					// Flash Settings
					flash_url : "<%=request.getContextPath()%>/swfupload1/swfupload.swf",

					custom_settings : {
						progressTarget : "divFileProgressContainer",
						cancelButtonId : "btnUpload"
					},
					use_query_string : false,//Ҫ���ݲ�������������,���ԴӺ�̨ȡ������,Ӧ�û���������ʽ����post��ʽ��δ�˽� 
					// Debug Settings
					debug : false
				//�Ƿ���ʾ���Դ�
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
				<input id="btnUpload"type="button" value="��  ��" style="height: 23px; display: none"
onclick="startUploadFile();" />
			</div>
			<br>
			<div id="divFileProgressContainer"
				style="border: none; margin-top: 8px;" value="dd"></div>
		</tr>
	</table>
</body>
</html>
