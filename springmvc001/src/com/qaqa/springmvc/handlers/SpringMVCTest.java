package com.qaqa.springmvc.handlers;

import com.qaqa.springmvc.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.portlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * Created by thinkpad on 2018/6/29.
 */

@SessionAttributes(value = {"user"}, types = {String.class})
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {

    private static final String SUCCESS = "success";

    @RequestMapping("/testViewAndViewResolver")
    public String testViewAndViewResolver() {
        System.out.println("testViewAndViewResolver");
        return SUCCESS;
    }

    //1. 有@ModelAttribute标记的方法,会在每个目标方法执行之前被SpringMVC调用!
    //2. @ModelAttribute 注解也可以来修饰目标方法POJO类型的入参,其value属性值有如下的作用:
    //  1). SpringMVC 会使用value属性值在 implicitModel 中查找对应对象,若存在则会直接传入到目标方法的入参中
    //  2). SpringMVC 会以value为key,POJO类型的对象为value，存入到request中
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", required = false) Integer id
            , Map<String, Object> map) {
        System.out.println("modelAttribute method");
        if (id != null) {
            //模拟从数据库中获取对象
            User user = new User(1, "Tom", "123", "Tom@123.com", 12);
            System.out.println("从数据库中获取一个对象: " + user);
            map.put("abc", user);
        }
    }

    //运行流程:
    //1. 执行@ModelAttribute注解修饰的方法: 从数据库中取出对象,把对象放入到了Map中 键为user
    //2. SpringMVC从Map中取出User对象,并把表单的请求参数赋给该User对象的对应属性
    //3. SpringMVC把上述对象传入目标方法的参数
    //
    //注意: 在ModelAttribute修饰的方法中,放入到Map时的键需要和目标方法入参类型的第一个字母小写的字符串一致
    //
    // SpringMVC确定目标方法POJO类型入参的过程
    // 1. 确定一个key:
    //  1).若目标方法的POJO类型的参数木有使用 @ModelAttribute 作为修饰,则key为POJO类名第一个字母的小写
    //  2).若使用了 @ModelAttribute来修饰,则key为 @ModelAttribute注解的value属性值
    // 2. 在implicitModel 中查找key对应的对象,若存在,则作为入参传入
    //  1).若在 @ModelAttribute标记的方法中在Map中保存过,且key和1确定的key一致,则会获取到
    // 3. 若implicitModel 中不存在key对应的对象,则检查当前的Handler是否存在 @SessionAttributes注解修饰
    // ,若使用了该注解,且 @SessionAttributes 注解的value属性值中包含了key,则会从HttpSession中来获取key
    // 所对应的value值,若存在则直接传入到目标方法的入参中,若不存在则将抛出异常
    // 4. 若Handler没有表示 @SessionAttributes注解或 @SessionAttributes 注解的value值中不包含key, 则
    // 会通过反射来创建POJO类型的参数,传入为目标方法的参数
    // 5. SpringMVC会把key和value保存到implicitModel中, 进而会保存到request中
    //
    //源代码分析的流程
    //1. 调用@ModelAttribute注解修饰的方法.实际上把 @ModelAttribute 方法中Map中的数据放在了 implicitModel 中
    //2. 解析请求处理器的目标参数,实际上该目标参数来自于 WebDataBinder 对象的target属性
    //1). 创建WebDataBinder对象:
    //  ①.确定objectName属性: 如果传入的attrName属性值为"",则objectName为类名第一个字母小写
    //  *注意: attrName. 若目标方法POJO属性使用了 @ModelAttribute 来修饰
    //  ,则attrName值即为 @ModelAttribute的value属性值
    //
    //  ②.确定target属性:
    //  > 在implicitModel中查找attrName对应的属性值. 若存在, ok
    //  > *若不存在:则验证当前Handler是否使用 @SessionAttributes 进行修饰,若使用了
    //  ,则尝试从Session中获取 attrName 所对应的属性值.若session中没有对应的属性值,则抛出异常
    //  > 若Handler没有使用 @SessionAttributes 进行修饰,或 @SessionAttributes中没有使用value值指定的key
    //  和attrName相匹配,则通过反射创建了POJO对象
    //2). SpringMVC把表单的请求参数赋给了WebDataBinder的target对应的属性
    //3). *SpringMVC会把WebDataBinder的attrName和target给到implicitModel. 进而传到request域对象中
    //4). 把WebDataBinder的target作为参数传递给目标方法的入参
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("mnxyz") User user) {
        System.out.println("修改:" + user);
        return SUCCESS;
    }

    //@SessionAttributes 除了可以通过属性名指定需要放到会话中的属性外(实际上使用的是value属性值)
    // ,还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中(实际上使用的是types属性值)
    // 注意: 该注解只能放在类的上面,而不能修饰方法
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String, Object> map) {
        User user = new User(1,"Tom", "123", "a@123.com", 15);
        map.put("user", user);
        map.put("school", "qwe");
        return SUCCESS;
    }

    //目标方法可以添加Map类型(实际上也可以是Model类型或者ModelMap类型)的参数
    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map) {
        System.out.println(map.getClass().getName());
        map.put("names", Arrays.asList("tom", "Jerry", "Mile"));
        return SUCCESS;
    }

    //目标方法的返回值可以是ModelAndView类型
    //其中可以包含视图和模型信息
    //SpringMVC会把ModelAndView的model中数据放入到request域对象中
//    @RequestMapping("/testModelAndView")
//    public ModelAndView testModelAndView() {
//        String viewName = SUCCESS;
//        ModelAndView modelAndView = new ModelAndView(viewName);
//
//        //添加模型数据到ModelAndView中
//        modelAndView.addObject("time", "abc");
//
//        return modelAndView;
//    }

    //可以使用Servlet原生的API作为目标方法的参数 具体支持一下类型
    //HttpServletRequest
    //HttpServletResponse
    //HttpSession
    //java.security.Principal
    //Locale InputStream
    //OutputStream
    //Reader
    //Write
    @RequestMapping("/testServletAPI")
    public void testServletAPI(HttpServletRequest request
            , HttpServletResponse response, Writer out) throws IOException {
        System.out.println("testServletAPI: " + request + "," + response);
        out.write("hello springmvc");
//        return SUCCESS;
    }

    //SpringMVC会按请求参数名和POJO属性名进行自动匹配, 自动为该对象填充属性值.
    //支持级联属性,如:dept.deptId dept.address.tel等
    @RequestMapping("/testPojo")
    public String testPojo(User user) {
        System.out.println("testPojo:" + user);
        return SUCCESS;
    }

    //了解:
    //@CookieValue: 映射一个Cookie值, 属性同@RequestParam
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String sessionId) {
        System.out.println("testCookieValue: sessionId:" + sessionId);
        return SUCCESS;
    }

    //了解:
    //映射请求头信息
    //用法同@RequestParam
    @RequestMapping("testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
        System.out.println("testRequestHeader, Accept-Language：" + al);
        return SUCCESS;
    }

    //@RequestParam来映射请求参数
    //value值即请求参数的参数名
    //required 该参数是否必须, 默认为true
    //defaultValue 请求参数的默认值
    @RequestMapping(value = "testRequestParam")
    public String testRequestParam(@RequestParam(value = "username") String username,
                                   @RequestParam(value = "age", required = false, defaultValue = "0") int age) {
        System.out.println("testRequestParam, username: " + username + ", age: " + age);
        return SUCCESS;
    }

    //Rest风格的URL.
    //以CRUD为例:
    //新增: /order POST
    //修改: /order/1 PUT  update?id=1
    //获取: /order/1 GET  get?id=1
    //删除: /order/1 DELETE   delete?id=1
    //
    //如何发送put请求和delete请求呢?
    //1. 需要配置HiddenHttpMethodFilter
    //2. 需要发送POST请求
    //3. 需要在发送POST请求时携带一个name="_method"的隐藏域,值为DELETE或PUT
    //
    //在SpringMVC的目标方法中如何得到id呢?
    //使用@PathVariable注解
    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
    public String testRestPut(@PathVariable Integer id) {
        System.out.println("testRest PUT: " + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable Integer id) {
        System.out.println("testRest DELETE: " + id);
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest", method = RequestMethod.POST)
    public String testRest() {
        System.out.println("testRest POST: ");
        return SUCCESS;
    }

    @RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
    public String testRest(@PathVariable Integer id) {
        System.out.println("testRest GET: " + id);
        return SUCCESS;
    }

    // @PathVariable 可以来映射URL中的占位符到目标方法的参数中
    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") Integer id){
        System.out.println("testPathVariable: " + id);
        return SUCCESS;
    }

    //了解: Ant通配符
    // ?: 一个字符  *:任意字符  **:多层路径
    @RequestMapping("/testAntPath/*/abc")
    public String testAntPath() {
        System.out.println("testAntPath");
        return SUCCESS;
    }

    //了解: 可以使用params和headers来更加精确的映射请求. params和headers支持简单的表达式
    @RequestMapping(value = "testParamsAndHeaders", params = {"username", "age!=10"}
    , headers = {"Accept-Language: zh-CN,zh;q=0.9"})
    public String testParamsAndHeaders() {
        System.out.println("testParamsAndHeaders");
        return SUCCESS;
    }

    //使用method属性来指定请求方式
    @RequestMapping(value = "/testMethod", method = RequestMethod.POST)
    public String testMethod() {
        System.out.println("testMethod");
        return SUCCESS;
    }

    //1. @RequestMapping除了修饰方法,还可来修饰类
    //2.
    // 1) 类定义处: 提供初步的请求映射信息.相对于WEB应用的根目录.
    // 2) 方法处: 提供进一步的细分映射信息.相对于类定义的URL.
    //   若类定义处未标注@RequestMapping,则方法处标记的URL相对于WEB应用的根目录
    @RequestMapping("testRequestMapping")
    public String testRequestMapping() {
        System.out.println("testRequestMapping");
        return SUCCESS;
    }
}
