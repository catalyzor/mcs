<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <dl>
                <dt>
                    车牌号码
                </dt>
                <dd>
                    ${code.carNumber}
                </dd>
                <dt>
                    车主电话
                </dt>
                <dd>
                    <a id="phone" href="tel:${phone}">******</a><button onclick="showCall()">拨打电话</button>
                </dd>

            </dl>
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
<script>
    function showCall() {
        $.ajax({
            url: '/show/vphone/' + ${code.code},
            type: 'post',
            success: function (data, status) {
                var tel = 'tel:' + data;
//                alert($('a#phone').attr('href'));
                $('a#phone').attr('href',tel);
                $('a#phone').text(data);
                $('button').hide();
            },
            fail: function (err, status) {
                console.log(err)
            }
        })
    }
</script>
</body>
</html>