<%--
  Created by IntelliJ IDEA.
  User: thinkpad
  Date: 2018/7/5
  Time: 1:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
      <script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
      <script type="text/javascript">
          $(function() {
              $("#testJson").click(function() {
                  var url = this.href;
                  var args = {};
                  $.post(url, args,function(data) {
                      for(var i=0 ;i<data.length;i++) {
                          var id = data[i].id;
                          var lastname = data[i].lastname;
                          alert(id+":"+lastname);
                      }
                  });
                  return false;
              })
          })
      </script>
  </head>
  <body>

  <form action="testFileUpload" method="post" enctype="multipart/form-data">
      File:<input type="file" name="file">
      Desc:<input type="text" name="desc">
      <input type="submit" value="Submit"/>
  </form>
  <br/>

  <a href="emps">List All Employees</a>

  <br/>
  <a href="testJson" id="testJson">Test Json</a>

  <br/>
  <form action="testHttpMessageConverter" method="post" enctype="multipart/form-data">
      File:<input type="file" name="file">
      Desc:<input type="text" name="desc">
      <input type="submit" value="Submit"/>
  </form>

  <br/>
  <a href="testResponseEntity">下载</a>
  <br/>

  <%--
    关于国际化:
    1. 在页面上能够根据浏览器语言设置的情况对文本(不是内容),时间,数值进行本地化处理
    2. 可以在bean中获取国际化资源文件Locale对应的消息
    3. 可以通过超链接切换Locale,而不再依赖于浏览器语言的设置情况

    解决:
    1. 使用JSTL的fmt标签
    2. 在bean中注入ResourceBundleMessageSource的示例,使用其对应的getMessage方法即可
    3. 配置LocalResolver 和 LocaleChangeInterceptor
  --%>
  <a href="i18n">I18N page</a>

  <br/>
  <a href="testExceptionHandlerExceptionResolver?i=10">Test ExceptionHandlerExceptionResolver</a>

  <br/>
  <a href="testResponseStatusExceptionResolver?i=10">Test ResponseStatusExceptionResolver</a>

  <br/>
  <a href="testDefaultHandlerExceptionResolver">Test DefaultHandlerExceptionResolver</a>

  <br/>
  <a href="testSimpleMappingExceptionResolver">Test SimpleMappingExceptionResolver</a>

  </body>
</html>
