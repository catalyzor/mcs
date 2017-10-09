<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>收费标准</title>
    <link rel="stylesheet" href="./common/css/reset.css">
    <link rel="stylesheet" href="./common/css/style.css">
    <link rel="stylesheet" href="./css/fees.css">
</head>

<body>
    <header class="head">
        <a href="javascript:;"></a>
        <h4>收费标准</h4>
    </header>
    <form id="fm" action="/orders" method="post">
        <input type="hidden" name="productNumber" id="productNumber" value="1"/>
    <section class="section">
        <div class="member">
            <img src="./img/fee_01.png" alt="">
        </div>
        <div class="member-price">
            <img src="./img/fee_02.png" alt="" onclick="doPost('1')">
        </div>
        <div class="member-price">
            <img src="./img/fee_03.png" alt="" onclick="doPost('2')">
        </div>
        <div class="member-price">
            <div class="state-wrap">
                <h3>资费说明</h3>
                <p>1、资费的说明，有两种方式，年费会员3.98元久使用9.98</p>
                <p>2、资费的说明，有两种方式，年费会员3.98元，永久使</p>
                <p>3、资费的说明，有两种方式，年费会员3.98元</p>
                <p>4、资费的说明，有两种方式</p>
                <p>5、资费的说明，有两种方式，年费会员3.98元，永久</p>
            </div>
        </div>


    </section>
    </form>

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

        function doPost(type) {
//            $.post({
//                url: '/orders',
//                type: 'post',
//                data: 'productNumber=' + type,
//                success: function (data, status) {
//                    console.log(data);
//                    window.location = data;
//                },
//                fail: function (err, status) {
//                    console.log(err)
//                }
//            })
            console.log(type);
            $('#productNumber').value = type;
            console.log($('#productNumber').val());
            $('#fm').submit();
        }
    </script>

    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.js"></script>
</body>

</html>