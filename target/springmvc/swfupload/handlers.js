function cancelQueue(instance) {
    document.getElementById(instance.customSettings.cancelButtonId).disabled = true;
    instance.stopUpload();
    var stats;
    do {
        stats = instance.getStats();
        instance.cancelUpload();
    } while (stats.files_queued !== 0);
}
function fileDialogStart() {
}
function fileQueued(file) {
    try {
        var progress = new FileProgress(file, this.customSettings.progressTarget);
        progress.setStatus("Pending...");
        progress.toggleCancel(true, this);
    } catch (ex) {
        this.debug(ex);
    }
}
function fileQueueError(file, errorCode, message) {
    try {
        if (errorCode === SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED) {
            //alert("最多可上传5张图片！");
            //alert("You have attempted to queue too many files.\n" + (message === 0 ? "You have reached the upload limit." : "You may select " + (message > 1 ? "up to " + message + " files." : "one file.")));
            return;
        }
        if(errorCode == SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT) {
            alert("文件太大，请选用大小在5M以下的图片!");
            return;
        }
        var progress = new FileProgress(file, this.customSettings.progressTarget);
        progress.setError();
        progress.toggleCancel(false);
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:

                progress.setStatus("File is too big.");
                this.debug("Error Code: File too big, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                progress.setStatus("Cannot upload Zero Byte files.");
                this.debug("Error Code: Zero byte file, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                progress.setStatus("Invalid File Type.");
                this.debug("Error Code: Invalid File Type, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                alert("You have selected too many files.  " + (message > 1 ? "You may only add " + message + " more files" : "You cannot add any more files."));
                break;
            default:
                if (file !== null) {
                    progress.setStatus("Unhandled Error");
                }
                this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
        }
    } catch (ex) {
        this.debug(ex);
    }
}
function fileDialogComplete(numFilesSelected, numFilesQueued) {
    try {
        if (this.getStats().files_queued > 0) {
            document.getElementById(this.customSettings.cancelButtonId).disabled = false;
        }
        this.startUpload();
    } catch (ex) {
        this.debug(ex);
    }
}
function uploadStart(file) {
    try {
        var progress = new FileProgress(file, this.customSettings.progressTarget);
        progress.setStatus("Uploading...");
        progress.toggleCancel(true, this);
    }
    catch (ex) {
    }
    return true;
}
function uploadProgress(file, bytesLoaded, bytesTotal) {
    try {
        var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
        var progress = new FileProgress(file, this.customSettings.progressTarget);
        progress.setProgress(percent);
        progress.setStatus("Uploading...");
    } catch (ex) {
        this.debug(ex);
    }
}
function uploadSuccess(file, serverData) {
    try {
        showimg(serverData);
        addImage(serverData);
        var progress = new FileProgress(file, this.customSettings.progressTarget);
        progress.setComplete();
        progress.setStatus("Complete.");
        progress.toggleCancel(false);
    } catch (ex) {
        this.debug(ex);
    }
}
function uploadComplete(file) {
    try {
        if (this.getStats().files_queued === 0) {
            document.getElementById(this.customSettings.cancelButtonId).disabled = true;
        } else {
            this.startUpload();
        }
    } catch (ex) {
        this.debug(ex);
    }
}
function addImage(src) {
    var newImg = document.createElement("img");
    newImg.style.margin = "5px";
    document.getElementById("thumbnails").appendChild(newImg);
    if (newImg.filters) {
        try {
            newImg.filters.item("DXImageTransform.Microsoft.Alpha").opacity = 0;
        } catch (e) {
            newImg.style.filter = 'progid:DXImageTransform.Microsoft.Alpha(opacity=' + 0 + ')';
        }
    } else {
        newImg.style.opacity = 0;
    }
    newImg.onload = function () {
        fadeIn(newImg, 0);
    };
    newImg.src = src;
    newImg.width = "100";
    newImg.height = "100";
}
function fadeIn(element, opacity) {
    var reduceOpacityBy = 5;
    var rate = 30;
    if (opacity < 100) {
        opacity += reduceOpacityBy;
        if (opacity > 100) {
            opacity = 100;
        }
        if (element.filters) {
            try {
                element.filters.item("DXImageTransform.Microsoft.Alpha").opacity = opacity;
            } catch (e) {
                element.style.filter = 'progid:DXImageTransform.Microsoft.Alpha(opacity=' + opacity + ')';
            }
        } else {
            element.style.opacity = opacity / 100;
        }
    }
    if (opacity < 100) {
        setTimeout(function () {
            fadeIn(element, opacity);
        }, rate);
    }
}
function uploadError(file, errorCode, message) {
    try {
        var progress = new FileProgress(file, this.customSettings.progressTarget);
        progress.setError();
        progress.toggleCancel(false);
        switch (errorCode) {
            case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
                progress.setStatus("Upload Error: " + message);
                this.debug("Error Code: HTTP Error, File name: " + file.name + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
                progress.setStatus("Configuration Error");
                this.debug("Error Code: No backend file, File name: " + file.name + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
                progress.setStatus("Upload Failed.");
                this.debug("Error Code: Upload Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.IO_ERROR:
                progress.setStatus("Server (IO) Error");
                this.debug("Error Code: IO Error, File name: " + file.name + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
                progress.setStatus("Security Error");
                this.debug("Error Code: Security Error, File name: " + file.name + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
                progress.setStatus("Upload limit exceeded.");
                this.debug("Error Code: Upload Limit Exceeded, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
                progress.setStatus("File not found.");
                this.debug("Error Code: The file was not found, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
                progress.setStatus("Failed Validation.  Upload skipped.");
                this.debug("Error Code: File Validation Failed, File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
            case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:
                if (this.getStats().files_queued === 0) {
                    document.getElementById(this.customSettings.cancelButtonId).disabled = true;
                }
                progress.setStatus("Cancelled");
                progress.setCancelled();
                break;
            case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:
                progress.setStatus("Stopped");
                break;
            default:
                progress.setStatus("Unhandled Error: " + error_code);
                this.debug("Error Code: " + errorCode + ", File name: " + file.name + ", File size: " + file.size + ", Message: " + message);
                break;
        }
    } catch (ex) {
        this.debug(ex);
    }
}