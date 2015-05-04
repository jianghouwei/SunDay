$(document).ready(function () {
	getFlashBuyControll();
	initUploadControl();
	scollUploadImg();
	checkIsGiftOrder();
	sellerNamePrompt();
	checkJdServiceProduct();
		initRemoveTheSingleGife();

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
	jQuery(".img-list li").Jdropdown();
}
var swfu;
//上传图片
function initUploadControl() {
	var url = "http://myjd.jd.com/afs/common/upload.action";
	var settings = {
		flash_url : "/swfupload/swfupload.swf", // Relative to this file
		flash9_url : "/swfupload/swfupload_FP9.swf", // Relative to this file
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
		button_image_url: "http://misc.360buyimg.com/jd2008/skin/df/i/btn-up.png",
		button_width: "75",
		button_height: "25",
		button_placeholder_id: "spanButtonPlaceHolder",
		file_dialog_start_handler: fileDialogStart,
		file_queued_handler: fileQueued,
		file_queue_error_handler: fileQueueError,
		file_dialog_complete_handler: fileDialogComplete,
		upload_start_handler: function () {
			var imgSize = c.uploadImgs.length;
			if (imgSize == 5) {
				showInputTips('mscolluploadImg-info', '抱歉，您上传的图片数已达总数5张,不能再继续上传了。', 'btnCancel', 'button');
				swfu.cancelUpload(undefined, false);
			}
		},
		upload_progress_handler: function () {
			showInputTips('mscolluploadImg-info', c.loading + '图片上传中...', 'btnCancel', 'button');
		},
		upload_error_handler: function (e) {
			showInputTips('mscolluploadImg-info', '上传图片网络异常,请稍后重试.', 'btnCancel', 'button');
		},
		upload_success_handler: function (f, d, r) {
			d = eval('(' + d + ')');
			if (d.optState == 1) {
				var scollupViewState = jQuery("div[name='mscolluploadImg']").css('display');
				if (scollupViewState == 'none') {
					jQuery("div[name='mscolluploadImg']").show();
				}
				c.uploadImgs.push(d.optDescription);
				//c.uploadImgs.reverse();
				jQuery("ul[name='mscolluploadImg-list']").empty();
				for (var i = 0; i < c.uploadImgs.length; i++) {
					var img = c.uploadImgs[i];
					var imgSrc = 'http://img30.360buyimg.com/myjd/' + img;
					var thumbnail = 'http://img30.360buyimg.com/asf/s60x60_' + img;
					var listSize = jQuery("ul[name=mscolluploadImg-list] li").length;
					var li_name = "mscolluploadImg-list-li_" + (listSize + 1);
					var li = "<li name='" + li_name + "'><a href=\"" + imgSrc + "\" target=\"_blank\"><img class=\"err-product\" width=\"50\" height=\"50\" src=\"" + thumbnail + "\" data-img='1'></a><b onclick=\"delApplyUploadImg('" + img + "')\">&times;</b></li>";
					jQuery("ul[name='mscolluploadImg-list']").prepend(li);
				}
				scollUploadImg();
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
