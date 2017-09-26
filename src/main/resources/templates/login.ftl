<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>login</title>
    <link rel="stylesheet" href="/common/css/reset.css">
    <link rel="stylesheet" href="/common/css/style.css">
    <link rel="stylesheet" href="/css/login.css">
</head>

<body>
    <div class="body-bg">
        <h2 class="header">
            <a href="javscript:;">
                <img src="/img/logo.png" alt="">
            </a>
        </h2>
        <div class="input-wrap">
            <form action="/users" method="post">
                <label for="erweima" class="input-wrapper">
				<div>
                    <p><img src="/img/erweima.png" alt=""></p>
                </div>
				<input type="text" id="inputCode" name="inputCode" value="${code}" placeholder="自动获取二维码">
			</label>
                <label for="erweima" class="input-wrapper">
				<div><p><img src="/img/chepai.png" alt=""></p></div>
				<input type="text" id="inputCarNumber" name="inputCarNumber" placeholder="输入车牌号">
			</label>
                <label for="erweima" class="input-wrapper">
				<div><p><img src="/img/phone.png" alt=""></p></div>
				<input type="text" id="inputPhone" name="inputPhone" placeholder="输入手机号">
			</label>
                <label for="erweima" class="input-wrapper">
				<div><p><img src="/img/confire.png" alt=""></p></div>
				<input type="text" id="erweima" placeholder="确认手机号">
			</label>
                <p class="hint">密码为6-20位数字、字母组合，可含下划线</p>
                <button class="btn-login">
                    注册
                </button>
                <p class="agree"><a href="javascript:;">注册即视为同意本网站《隐私条款》</a></p>
            </form>
        </div>
        <div class="line">

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
    </div>
</body>

</html>