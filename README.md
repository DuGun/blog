一个前后端分离的个人博客。<br/>
帅哥前端人士：JavaLake
<br/>
屌丝后端人士：脐橙
<br/>
屌丝安卓人士：哽咽
<br/>
<a href="http://blog.javalake.top/">前端网址</a>
<a href="https://github.com/DuGun/blog/blob/master/exploitBlogData/blog.sql">mysql脚本</a>
<br/>
<br/>
<h1>后台所用的技术</h1>
<ul>
<li>SSM框架</li>
<li>MySql数据库</li>
<li>Redis缓存数据库</li>
  <li>Shiro权限框架</li>
  <li>validation校验框架</li>
  <li>ToKen</li>
  <li>雪花算法生成ID</li>
  <li>FileUtils</li>
  <li>FilenameUtils</li>
</ul>
<br/>
<h1>遇到的问题</h1>
<ul>
  <li>单/多文件上传存在不定性：可以通过将Request强转成MultipartHttpServletRequest，然后通过getFiles方法进行获取</li>
  <li>使用validation校验框架时使用@RequestBody导致不是抛出BindException，而是MethodArgumentNotValidException，其次就是获取异常信息。</li>
  <li>在使用校验框架的时候，对于基本类型的校验，且该参数是可有可无，这得必须使用基本类型的包装类型</li>
  <li>上一个demo项目中在使用Redis进行缓存Shiro权限类还是角色类出现复杂对象无法正常反序列化：使用Gson进行自定义反序列化</li>
  <li>使用JDK8新日期API中解决并发可能出现的时间问题</li>
  <li>在阿里学生服务器中出现MySQL数据库出现全库被抹：至于原因暂时不知道</li>
</ul>

<br/>
<h1>已存在的问题</h1>
<ul>
  <li>数据库中外健
    <ul>
      <li>该外健交由MySql数据库处理，并没有交由应用层处理</li>
      <li>根据阿里巴巴Java开发手册写道：外健与级联更新适用于单机低并发，不适合分布式、高并发集群；级联更新是强阻塞，存在数据库更新风暴的风险；外健影响数据库的插入速度</li>
    </ul>
  </li>
  <li>HashMap集合初始化
    <ul>
      <li>在使用HashMap时，集合长度在运行期才能确定，而没有通过代码及时初始化HashMap</li>
      <li>从HashMap底层代码及阿里巴巴Java开发手册道：没有对HashMap设置容量初始化大小，随着元素不断增加，扩容次数可能会升高，导致扩容时不断重建Hash表，严重影响性能</li>
    </ul>
  </li>
</ul>

<br/>
<h1>需要注意的问题</h1>
<ul>
  <li>在对集合进行循环删除，建议使用迭代器</li>
</ul>

<br/>
<br/>
<h1>数据库外健关联图</h1>

![image](https://github.com/DuGun/blog/blob/master/exploitBlogData/%E5%A4%96%E5%81%A5%E9%99%90%E5%88%B6%E5%9B%BE%20%E4%B8%8B%E5%8D%887.35.45.jpg)


<h1>数据库设计</h1>

![image](https://github.com/DuGun/blog/blob/master/exploitBlogData/WechatIMG6.png)

![image](https://github.com/DuGun/blog/blob/master/exploitBlogData/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1(%E4%BA%8C).png)

![image](https://github.com/DuGun/blog/blob/master/exploitBlogData/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1(%E4%B8%89).png)

![image](https://github.com/DuGun/blog/blob/master/exploitBlogData/%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1(%E5%9B%9B).png)

