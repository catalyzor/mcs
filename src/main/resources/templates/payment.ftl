<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="./common/css/reset.css">
    <link rel="stylesheet" href="./common/css/style.css">
    <link rel="stylesheet" href="./css/payment.css">
</head>

<body>
    <header class="head">
        <a href="javascript:;"></a>
        <h4>选择支付方式</h4>
    </header>
    <div class="total-price">
        <h3>订单总金额：￥<span>20</span></h3>
    </div>
    <div class="payment">
        <ul>
            <li>
                <a href="javascript:;">
                    <div class="title">
                        <img src="./img/icon_07.png" alt="">
                    </div>
                    <div class="pay-right">
                        <h3>支付宝</h3>
                        <p>支持有支付宝，网银的用户使用</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="javascript:;">
                    <div class="title">
                        <img src="./img/icon_10.png" alt="">
                    </div>
                    <div class="pay-right">
                        <h3>微信支付</h3>
                        <p>使用微信支付，安全便捷</p>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <button class="btn-payment">
        确认支付
    </button>

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
</body>

</html>