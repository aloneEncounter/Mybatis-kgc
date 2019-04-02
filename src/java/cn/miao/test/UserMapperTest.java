package cn.miao.test;

import cn.miao.dao.UserMapper;
import cn.miao.pojo.Address;
import cn.miao.pojo.Role;
import cn.miao.pojo.User;
import cn.miao.utils.MyBatisUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserMapperTest {

    private Logger logger = Logger.getLogger(UserMapperTest.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void test() {
        String resource = "mybatis-config.xml";
        int count = 0;
        SqlSession sqlSession = null;
        try {
            /*//1、获取mybatis-config.xml 的输入流
            InputStream is= Resources.getResourceAsStream(resource);

            //2、创建SqlsessionFactory对象，完成对配置文件的读取
            SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);

            //3、创建Sqlsession对象
            //sqlSession=factory.openSession();*/

            sqlSession = MyBatisUtil.createSqlSession();

            //4、调用mapper文件来对数据进行操作,必须将mapper文件引入mybatis-config.xml文件中
//            count=sqlSession.selectOne("mapper.UserMapper.count");
            count = sqlSession.selectOne("cn.miao.dao.UserMapper.count");

            logger.debug("UserMapperTest count----->" + count);

//        } catch (IOException e) {
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            sqlSession.close();
            MyBatisUtil.closeSqlSession(sqlSession);
        }
    }

    @Test
    public void testGetUserList() {

        List<User> list = null;

        SqlSession sqlSession = null;
        User user = new User();
        user.setUserName("赵");
        user.setUserRole(2);
        try {

            sqlSession = MyBatisUtil.createSqlSession();

//            list = sqlSession.selectList("cn.miao.dao.UserMapper.getUserList");

            //通过代理获取  接口的方法名必须与 mapper文件中 的sql语句 所对应的id名字一样
            list = sqlSession.getMapper(UserMapper.class).getUserList(user);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
//        list.stream().forEach(System.out::println);
        for (User user1 : list) {
            System.out.println(user1.getUserCode() + "-----" + user1.getUserName() + "-->" + user1.getUserRoleName());
        }
    }

    @Test
//    使用自定义结果集合
//    动态SQL IF
    public void testGetUserList1() {

        List<User> list = null;

        SqlSession sqlSession = null;
        /*User user = new User();
        user.setUserName("赵");
        user.setUserRole(2);*/
        String userName="孙";
        Integer roleId=null;
        try {

            sqlSession = MyBatisUtil.createSqlSession();

//            list = sqlSession.selectList("cn.miao.dao.UserMapper.getUserList");

            //通过代理获取  接口的方法名必须与 mapper文件中 的sql语句 所对应的id名字一样
            list = sqlSession.getMapper(UserMapper.class).getUserList1(userName,roleId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
//        list.stream().forEach(System.out::println);
        for (User user1 : list) {
            System.out.println(user1.getUserCode() + "---" + user1.getUserName() +
                                       "-->" + user1.getUserRoleName() + "--" + user1.getAddress());
        }
    }

    @Test
//    使用自定义结果集合
//    动态SQL where
    public void testGetUserList2() {

        List<User> list = null;

        SqlSession sqlSession = null;
        /*User user = new User();
        user.setUserName("赵");
        user.setUserRole(2);*/
        String userName="";
        Integer roleId=3;
        try {

            sqlSession = MyBatisUtil.createSqlSession();

//            list = sqlSession.selectList("cn.miao.dao.UserMapper.getUserList");

            //通过代理获取  接口的方法名必须与 mapper文件中 的sql语句 所对应的id名字一样
            list = sqlSession.getMapper(UserMapper.class).getUserList2(userName,roleId);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
//        list.stream().forEach(System.out::println);
        for (User user1 : list) {
            System.out.println(user1.getUserCode() + "---" + user1.getUserName() +
                                       "-->" + user1.getUserRoleName() + "--" + user1.getAddress());
        }
    }

    @Test
//    使用自定义结果集合
//    根据 用户名，角色名   进行分页查询
    public void testGetUserList3() {

        List<User> list = null;

        SqlSession sqlSession = null;

        String userName="";
        Integer roleId=3;
        Integer currentPageNo=5;    //数据库中  下表默认是从零开始
        Integer pageSize=5;
        try {

            sqlSession = MyBatisUtil.createSqlSession();

            //通过代理获取  接口的方法名必须与 mapper文件中 的sql语句 所对应的id名字一样
            list = sqlSession.getMapper(UserMapper.class).getUserList3(userName,roleId,currentPageNo,pageSize);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
//        list.stream().forEach(System.out::println);
        for (User user1 : list) {
            System.out.println(user1.getUserCode() + "---" + user1.getUserName() +
                                       "-->" + user1.getUserRoleName() + "--" + user1.getAddress());
        }
    }


    @Test
    public void testGetUserListByMap() {

        List<User> list = null;
        SqlSession sqlSession = null;

        Map<String, String> userMap = new HashMap<>();
        userMap.put("uName", "赵");
        userMap.put("uRole", "2");

        try {
            sqlSession = MyBatisUtil.createSqlSession();
            list = sqlSession.getMapper(UserMapper.class).getUserListByMap(userMap);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        for (User user : list) {
            System.out.println(user.getUserCode() + "---->" + user.getUserName());
        }

    }


    @Test
    public void testGetUserListByUserName() {

        List<User> list = null;

        SqlSession sqlSession = null;

        String userName = "赵";
        try {
            sqlSession = MyBatisUtil.createSqlSession();

            list = sqlSession.getMapper(UserMapper.class).getUserListByUserName(userName);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            MyBatisUtil.closeSqlSession(sqlSession);
        }
        for (User user : list) {
            System.out.println(user.getUserCode() + "-----" + user.getUserName());
        }

    }

    //    增加
    @Test
    public void testAdd() {

        SqlSession sqlSession = null;
        int count = 0;

        try {
            sqlSession = MyBatisUtil.createSqlSession();
            User user = new User();
            user.setUserCode("test001");
            user.setUserName("测试用户001");
            user.setAddress("测试地址");
            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1995-12-12"));
            user.setUserPassword("123123");
            user.setUserRole(1);
            user.setCreateBy(1);
            user.setCreationDate(new Date());
            count = sqlSession.getMapper(UserMapper.class).add(user);
            //默认是开启事务的,出现异常可以回滚事务
//            int i=2/0
            sqlSession.commit();  //提交事务
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
            count = 0;
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        logger.debug("testAdd count------------------------" + count);
    }

    //    修改
    @Test
    public void testModify() {

        SqlSession sqlSession = null;
        int count = 0;

        try {
            sqlSession = MyBatisUtil.createSqlSession();
            User user = new User();
            user.setId(14);
//            user.setUserCode("test01");
            user.setUserName("李白");
            user.setAddress("测试地址");
//            user.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse("1995-12-12"));
//            user.setUserPassword("123123");
//            user.setUserRole(1);
            user.setModifyBy(1);
            user.setModifyDate(new Date());
            count = sqlSession.getMapper(UserMapper.class).modify(user);

            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        logger.debug("testModify ---------------->" + count);
    }

    @Test
    public void testUpdatePwd() {

        SqlSession sqlSession = null;
        int count = 0;
        try {
            sqlSession = MyBatisUtil.createSqlSession();

            /*User user =new User();
            user.setId(19);
            user.setUserPassword("miao123");*/

//            sqlSession.getMapper(UserMapper.class).updatePwd(user.getId(),user.getUserPassword());
            count = sqlSession.getMapper(UserMapper.class).updatePwd(19, "12245");

            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        logger.debug("testUpdatePwd--------------------->" + count);

    }

    @Test
    public void testDeleteUserById() {

        SqlSession sqlSession = null;
        int count=0;
        try {
            sqlSession = MyBatisUtil.createSqlSession();

            count=sqlSession.getMapper(UserMapper.class).deleteUserById(19);

            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }

        logger.debug("testDelete------------>"+count);
    }

    @Test
//    使用association
    public void testGetUseListByRoleId(){
        List<User> list=null;
        SqlSession sqlSession=null;

        try {
            Role role=new Role();
            role.setId(3);
            sqlSession=MyBatisUtil.createSqlSession();

            list=sqlSession.getMapper(UserMapper.class).getUserListByRoleId(role.getId());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        for (User user1 : list) {
            System.out.println(user1.getUserCode() + "-----" +user1.getUserName()+"--"+
                                       user1.getRole().getRoleCode() + "-->" + user1.getRole().getRoleName());
        }

    }

    @Test
//    使用collection映射
    public void testGetAddressListByUserId(){

        List<User> list=new ArrayList<>();
        SqlSession sqlSession=null;
        Integer userId=1;

        try {
            sqlSession=MyBatisUtil.createSqlSession();

           list= sqlSession.getMapper(UserMapper.class).getAddressListByUserId(userId );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        for (User user:list){
            logger.debug(user.getUserCode()+"--"+user.getUserName()+"<未作 映射>and userPassword"+
                                user.getUserPassword());

            for (Address address:user.getAddressList()){
                logger.debug("address--->"+address.getId()+",contact--->"+address.getContact()
                +"addressDesc--->"+address.getAddressDesc()+"user Id<未映射字段>："+address.getUserId());
            }
        }

    }
    @Test
//    根据用户角色列表 获取该角色列表下用户列表信息 foreach --array
//    使用for each
    public void testgetUserByRoleId_foreach_array(){

        List<User> list=new ArrayList<>();
        SqlSession sqlSession=null;
        Integer[] roleIds={2,3};

        try {
            sqlSession=MyBatisUtil.createSqlSession();

            list= sqlSession.getMapper(UserMapper.class).getUserByRoleId_foreach_array(roleIds);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        for (User user:list){
            logger.debug(user.getUserCode()+"--"+user.getUserName());

        }

    }

    @Test
//    根据用户角色列表 获取该角色列表下用户列表信息 foreach --array
//    使用for each  参数类型为List 时侯选用
    public void testgetUserByRoleId_foreach_list(){

        List<User> list=new ArrayList<>();
        SqlSession sqlSession=null;
        List<Integer> roleList=new ArrayList<>();
        roleList.add(2);
        try {
            sqlSession=MyBatisUtil.createSqlSession();

            list= sqlSession.getMapper(UserMapper.class).getUserByRoleId_foreach_list(roleList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        for (User user:list){
            logger.debug(user.getUserCode()+"--"+user.getUserName());

        }

    }


    @Test
//    根据用户角色列表 获取该角色列表下用户列表信息 foreach --array
//    使用for each  参数类型为List 时侯选用
    public void testGetUserByconditionMap_foreach_map(){

        List<User> list=new ArrayList<>();
        Map<String,Object> conditionMap=new HashMap<>();
        SqlSession sqlSession=null;
        List<Integer> roleList=new ArrayList<>();
        roleList.add(2);
        roleList.add(3);

        conditionMap.put("roleIds",roleList);
        conditionMap.put("gender",1);
        try {
            sqlSession=MyBatisUtil.createSqlSession();

            list= sqlSession.getMapper(UserMapper.class).getUserByconditionMap_foreach_map(conditionMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        for (User user:list){
            logger.debug(user.getUserCode()+"--"+user.getUserName());

        }

    }

    @Test
//    根据用户角色列表 获取该角色列表下用户列表信息 foreach --map
//    使用for each  将传入的一个参数，封装成Map对象
    public void testGetUserByRoleId_foreach_map(){

        List<User> list=new ArrayList<>();
        Map<String,Object> roleMap=new HashMap<>();
        SqlSession sqlSession=null;

        List<Integer> roleList=new ArrayList<>();
        roleList.add(2);
        roleList.add(3);

        roleMap.put("roleKey",roleList);
        try {
            sqlSession=MyBatisUtil.createSqlSession();

            list= sqlSession.getMapper(UserMapper.class).getUserByRoleId_foreach_map(roleMap);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        for (User user:list){
            logger.debug(user.getUserCode()+"--"+user.getUserName());

        }

    }



    @Test
    /*choose  when otherwise*/
    public void testGetUserList_choose(){

        List<User> list=new ArrayList<>();
        SqlSession sqlSession=null;
        String userName="";
        String userCode="";
        Integer userRole=1;

        try {
            Date creationDate=new SimpleDateFormat("yyyy-MM-dd").parse("2016-01-01");
            sqlSession=MyBatisUtil.createSqlSession();

            list= sqlSession.getMapper(UserMapper.class).getUserList_choose(userName,userCode,userRole,creationDate);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyBatisUtil.closeSqlSession(sqlSession);
        }
        for (User user:list){
            logger.debug(user.getUserCode()+"--"+user.getUserName());

        }

    }
}
