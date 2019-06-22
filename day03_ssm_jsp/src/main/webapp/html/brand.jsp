<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>品牌</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <h3>品牌功能</h3>
        <div class="row">
            <form class="form-inline" id="searchform" action="/brand/listBytow" method="post">
                <div class="form-group">
                    <label class="sr-only" for="exampleInputEmail3">品牌名称:</label>
                    <input type="text" class="form-control" name="name" id="exampleInputEmail3" placeholder="请输入品牌名称" value="${tb.name}">
                </div>
                <div class="form-group">
                    <label class="sr-only" for="exampleInputPassword3">品牌首字母:</label>
                    <input type="text" class="form-control" name="firstChar" id="exampleInputPassword3" placeholder="请输入品牌首字母" value="${tb.firstChar}">
                </div>
                <button type="submit" class="btn btn-success" id="searchbtn">搜索</button>
                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="qingKong()">
                    新增品牌
                </button>
                <button type="button" class="btn btn-success" id="daleteAll" onclick="piLiangDelete()">删除选中</button>
            </form>
        </div><br>
        <div class="row">
            <table class="table table-bordered table-hover" >
                <thead>
                    <tr <%--class="success"--%>>
                        <td><input type="checkbox" id="tou" onclick="touIsXuan()"></td>
                        <td>ID</td>
                        <td>品牌名称</td>
                        <td>品牌首字母</td>
                        <td>操作</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="brand" items="${list.list}" varStatus="i">
                    <tr <%--class="success"--%>>
                        <td><input type="checkbox" class="piLiang" value="${brand.id}" name="uid"></td>
                        <td>${i.count+(list.currentPage-1)*list.pageSize}</td>
                        <td>${brand.name}</td>
                        <td>${brand.firstChar}</td>
                        <td>
                            <%--<a href="${pageContext.request.contextPath}"/brand/listOne?id=${brand.id}"></a>--%>
                            <button class="btn btn-primary" data-toggle="modal" data-target="#myModal" onclick="bianJi(${brand.id},'${brand.name}','${brand.firstChar}')">编辑</button><%----%>
                            <a href=${pageContext.request.contextPath}"/brand/deleteByone?id=${brand.id}&currentPage=${list.currentPage}&totalCount=${list.totalCount}&name=${tb.name}&firstChar=${tb.firstChar}&totalPage=${list.totalPage}"><button class="btn btn-danger">删除</button></a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <%--分页--%>
        <div class="row">
            <nav aria-label="Page navigation">
                <ul class="pagination" class="pagination pagination-lg">
                    <li>
                        <a href=${pageContext.request.contextPath}"/brand/listBytow?currentPage=1&name=${tb.name}&firstChar=${tb.firstChar}" aria-label="Previous">
                            <span aria-hidden="true">首页</span>
                        </a>
                    </li>
                    <li>
                        <a href=${pageContext.request.contextPath}"/brand/listBytow?currentPage=${list.currentPage-1}&name=${tb.name}&firstChar=${tb.firstChar}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>

                    <c:forEach begin="${XD[0]}" end="${XD[1]}" varStatus="i">
                        <c:if test="${list.currentPage ==XD[0]+i.count-1}">
                            <li class="active"><a href=${pageContext.request.contextPath}"/brand/listBytow?currentPage=${XD[0]+i.count-1}&name=${tb.name}&firstChar=${tb.firstChar}">${XD[0]+i.count-1}<span class="sr-only">(current)</span></a></li>
                        </c:if>
                        <c:if test="${list.currentPage != XD[0]+i.count-1}">
                            <li><a href=${pageContext.request.contextPath}"/brand/listBytow?currentPage=${XD[0]+i.count-1}&name=${tb.name}&firstChar=${tb.firstChar}">${XD[0]+i.count-1}</a></li>
                        </c:if>
                    </c:forEach>
                    <li>
                        <c:if test="${list.currentPage+1>list.totalPage}">
                            <a href=${pageContext.request.contextPath}"/brand/listBytow?currentPage=${list.currentPage}&name=${tb.name}&firstChar=${tb.firstChar}" aria-label="Next">
                        </c:if>
                        <c:if test="${list.currentPage+1<=list.totalPage}">
                        <a href=${pageContext.request.contextPath}"/brand/listBytow?currentPage=${list.currentPage+1}&name=${tb.name}&firstChar=${tb.firstChar}" aria-label="Next">
                        </c:if>
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                    <li>
                        <a href=${pageContext.request.contextPath}"/brand/listBytow?currentPage=${list.totalPage}&name=${tb.name}&firstChar=${tb.firstChar}" aria-label="Next">
                            <span aria-hidden="true">末页</span>
                        </a>
                    </li>
                    <li>
                        <font size="5" style="color: red">共${list.totalCount}条${list.totalPage}页</font>
                    </li>
                </ul>
            </nav>
        </div>
    </div>

    </div>
<!--编辑窗口-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">编辑窗口</h4>
                </div>
                <div class="modal-body">
                    <form class="form-horizontal" id="brandForm" action=${pageContext.request.contextPath}"/brand/saveORupdata?currentPage=${list.currentPage}" method="post">
                        <input type="hidden" name="id" value="" id="hidden">
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">品牌名称</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="name" id="name" placeholder="请输入品牌名称" value="">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="firstChar" class="col-sm-3 control-label">品牌首字母</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="firstChar" id="firstChar" placeholder="请输入品牌首字母" value="">
                            </div>
                        </div>
                    </form>
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" id="editbtn">保存</button>
                </div>
            </div>
        </div>
    </div>




</body>

<script>
    /*批量删除*/
    function piLiangDelete() {
        var pl= $(".piLiang");
        var j=0;
        var s=new Array();
        for(var i=0;i<pl.length;i++){
            if( $(pl[i]).is(':checked')){
                var id = $(pl[i]).attr("value");
                s[j]=id;
                j++;
            }
        }
        location.href = "${pageContext.request.contextPath}/brand/daleteAll?ids=" + s;
    }
    /*批量选中*/
    function touIsXuan() {
        var pl= $(".piLiang");
        for(var i=0;i<pl.length;i++){
            $(pl[i]).prop("checked", $("#tou").is(':checked'));;
        }
    }
    /*编辑保存ID*/
    function bianJi(i,j,k) {
        $("#hidden").val(i);
        $("#name").val(j);
        $("#firstChar").val(k);
    }
    /*清空ID*/
    function qingKong() {
        $("#hidden").val("");
        $("#name").val("");
        $("#firstChar").val("");
    }

    $(function () {
        /*保存，修改提交*/
       $("#editbtn").click(function () {
           $("#brandForm").submit();
       })


    })


</script>
</html>