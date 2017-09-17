<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<#--<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <form>
                <fieldset>
                    <legend>注册</legend>
                    <label>手机号码</label><input type="text" /><p/>
                    <label>车牌号码</label><input type="text" /><p/>
                    <label>编码</label><input type="text" disabled="disabled" value="${code}"/>
                    <span class="help-block">这里填写帮助信息.</span>
                    <button type="submit" class="btn">提交</button>
                </fieldset>
            </form>
        </div>
    </div>
</div>-->
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <form class="form-horizontal" action="/users" method="post">
                <div class="control-group">
                    <label class="control-label" for="inputPhone">手机号码</label>
                    <div class="controls">
                        <input id="inputPhone" name="inputPhone" type="text" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputCarNumber">车牌号码</label>
                    <div class="controls">
                        <input id="inputCarNumber" name="inputCarNumber" type="text" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="inputCode">编号</label>
                    <div class="controls">
                        <input id="inputCode" name="inputCode" type="hidden" value="${code}"/>
                        <label class="control-box">${code}</label>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <label class="control-label"/>
                        <#--<label class="checkbox"><input type="checkbox" /> Remember me</label> -->
                        <button type="submit" class="btn">提交</button>  <button type="reset" class="btn">清空</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</body>
</html>