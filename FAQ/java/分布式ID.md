###ID生成的几种方式

_1. UUID_
  
_2. 数据库自增主键、序列_
  
_3. redis incr incrby_
  
_4. 雪花算法 snowflake_
  
```
第一种UUID，java自带的一种ID生成方式
    优点，简单好用，快速方便。适合一些数量不是很多的应用。
    简单的应用等。
    缺点，一长串字符串，mysql数据库建议id越小越好。
    而且UUID是无序的，插入数据库开销大。
    曾经的梅丽莎病毒开发者,
    人们就是根据机器码地址生成的UUID的找到了他。
```

```
第二种，通过数据库，例如自增主键，序列等。
    优点，也是简单好用，有序，比较依赖数据库
    可以通过增加数据库来提升并发。
    也可以通过设置步程来改变序列每次递增的值。
    还能使用分库分表。
     
    假设现在需要新增数据库，那么，在现有的ID的数值基础上。
    取一个靠后的比较大的数据，新机器从后面开始。
    例如 现在ID使用到了100w，新机器从200w开始。
    
    缺点，比较依赖数据库
    
    其中美团的leaf框架，就是基于数据库递增序列设计的
    
    leaf架构中，一次取用一个区间段的ID
    设计ID的最大值，以及每次区间的值。
    例如，最大值是1000w。每次取1w个ID来放到内存中使用
    
    leaf还推荐使用双buffer段使用。
    在拿到的第一个区间使用到一定程度后，就去去下个区间段。
```

```
第三种，redis，其实现与上方数据库实现方式类似。没有具体研究过。
    其基于内存，性能应该比数据库的要好。
```

```
第四种，雪花算法，推特开源的ID生成算法。
    通过 一位标志符，41位的时间戳，10位机器码，12位的序列 总共64位的2进制数来生成
    其10位机器码，最多可用1024台机器。
    12位序列，最多可用4096 0-4095。
    当一毫秒的序列超出4095，则等待下一秒，重新从0开始生成。
```

###时钟回拨问题