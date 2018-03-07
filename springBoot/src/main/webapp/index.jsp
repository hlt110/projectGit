<html>
<body>
<h2>Hello World!</h2>



<#list page.result as data>
<div class="Items clear">
    <div class="pink">
        <div class="clear Spanred">
            <div class="fl"><span>订单号:${data.ddbh}</span>  <span style="margin-left:30px;">买家:${data.username}</span></div>

            <div class="fr active"><span>${data.ddzt_val}</span></div>
        </div>
        <div class="clear Spanred">
            <div class="fl"><span>收货地址:${data.ddbh}</span></div>
            <div class="fl"><span style="margin-left:30px;">联系人:${data.lxr}</span><span style="margin-left:30px;">联系电话：${data.lxdh}</span></div>
        </div>
        <!--图片区-->
        <#list data.orderDetails as details>
        <div class="clear Img_user">
            <div class="fl"><img src="${uploadServerPath}${details.tpdz}" alt=""/></div>
            <div class="fl">
                <span>${details.spmc}</span>
                <#list details.xssxList as xssx>
                <p>${xssx.sxmc}:<span>${xssx.sxzmc}</span></p>
                 <#list>

        </div>
        <div class="fl">￥${details.dj}</div>
        <div class="fl">x${details.sl}</div>
        <#list>
    </div>


<div class="fr boc"><span>共${data.orderDetails?size}件商品</span><span>小计：</span><span>￥${data.ddhjje}</span><span>( 含运费￥${data.yunfei} )</span></div>
</div>
<div class="fl bor">

    <#if data.ddzt = 2>
    <a href="javascript:" class="active2 shipments" onclick="viewTh('${data.ddid}','${data.pssj}','${data.psy}','${data.psid}')">发货</a>
    <!-- *****删除******王金涛******已送达状态取消*************20180226***删除开始********** -->
    <!--  <#elseif data.ddzt = 5>
                   	<!--  <a href="javascript:void(0);" class="active2 shipments"  onclick="arriDd('${data.ddid}')" >已送达</a>  -->
    <!-- *************删除结束************* -->
<#if>
</div>
</div>
<#list>
</body>
</html>
