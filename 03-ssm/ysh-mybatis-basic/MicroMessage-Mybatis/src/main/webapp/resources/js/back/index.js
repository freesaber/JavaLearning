function deleteOne(id) {
    var encodeCommand = encodeURI(document.getElementById("command").value);
    var encodeDescription = encodeURI(document.getElementById("description").value);
    var path = '${basePath}DeleteOneServlet.action?id=' + id + '&command=' + encodeCommand + '&description=' + encodeDescription;
    window.location.href = path;
}

function deleteBatch(bathPath) {
    $("#mainForm").attr("action", bathPath + "DeleteBatchServlet.action");
    $("#mainForm").submit();
}