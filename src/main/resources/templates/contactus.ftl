<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="/common/css/reset.css">
    <link rel="stylesheet" href="/common/css/style.css">
    <link rel="stylesheet" href="/css/contactus.css">
</head>

<body>
    <header class="head">
        <a href="javascript:;"></a>
        <h4>扫码挪车</h4>
    </header>
    <div class="tell-wrapper">
        <div class="plate-number">
            <h2>车牌号</h2>
            <p>${code.carNumber}</p>
        </div>
        <div class="call-contain">
            <div class="call-wrap">
                <a href="javascript:showCall();">
                    <i class="icon"></i>
                    <h5>显示电话</h5>
                </a>
            </div>
        </div>
    </div>
    <div class="link-wrap">
        <div class="link">
            <h2>联系方式</h2>
        </div>
        <div class="link-detail">
            <div class="link-content">
                <h2>长春市...汽车有限公司</h2>
                <p>问题反馈电话 ：0431-88888888</p>
                <p>加盟电话：0431-99999999</p>
                <p>网址：www.123456.com</p>
            </div>
        </div>
    </div>

    <script>
        function fontSizeRoot() { //动态计算字体
            var ele = document.getElementsByTagName("html")[0];
            var kl = document.body.clientWidth;
            if (kl >= 768) {
                kl = 768
            }
            var size = kl / 320 * 20;
            ele.style.fontSize = size + "px"
        }
        fontSizeRoot();
        window.onresize = function() {
            fontSizeRoot();
        };
    </script>
    <script>
        function showCall() {
            $.ajax({
                url: '/show/vphone/' + ${code.code},
                type: 'post',
                success: function (data, status) {
                    var tel = 'tel:' + data;
//                alert($('a#phone').attr('href'));
                    $('h5').text(data);
//                    $('h5').attr('href',tel);
                    window.location=tel;
//                    $('button').hide();
                },
                fail: function (err, status) {
                    console.log(err)
                }
            })
        }
    </script>
</body>

<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
</html>