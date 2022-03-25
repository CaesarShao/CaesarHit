Android依赖注入框架-Hilt详解，官方基于Dagger封装适配Android而开发，史上最详细解析

记得2年前，我发布过一篇关于Android依赖注入框架的文章，Dagger在Android开发中上手难度较高，当时就给大家推荐了Koin,感兴趣的同学可以去看看那篇文章。https://www.jianshu.com/p/bccb93a78cee。（Koin与Hilt各有优势）

现在，Android有了官方的依赖注入工具，那就是Hilt,基于Dagger上的封装，所以Hilt跟Dagger很相似，谷歌官方也有专门的Hilt中文文档，不过版本比较老，是2.28版本，地址为：https://developer.android.google.cn/training/dependency-injection/hilt-android，感兴趣的同学可以去看看。本例是基于当前最新的版本：2.40.5版本所写，最新的版本跟老的版本有很多的差别，方法和参数都变动挺大，老的文档可能不适用于最新的版本。正所谓用新不用旧，如果想了解最新用法少走歪路的，可以看我这篇文章，算是比较详细的吧。

依赖注入是什么，依赖注入有什么好处，这些就不多讲了，直接去看我上面以前写的文章，我们直接进入Hilt的用法讲解。本篇的例子在我的github上，地址为：https://github.com/CaesarShao/CaesarHit，感兴趣的可以对照着理解。

![Hilt图片](/Users/caesar/Documents/Hilt图片.png)

本文目录

##### 依赖方式

##### 开始使用

##### Activity作用域--最基础调用

##### 全局作用域及单例模式

##### ViewModel中调用

##### Fragment作用域使用

##### Service作用域使用

##### 自定义View作用域

##### 嵌套使用

##### 重名的用法

##### 接口未指明用法

##### 其他类中获取方式

-----------------------

### 依赖方式

首先在根级的build.gradle中加入

```
buildscript {
    ...
    dependencies {
        ...
        classpath 'com.google.dagger:hilt-android-gradle-plugin:2.40.5'
    }
}
```

然后在app/build.gradle中加入

```
...
apply plugin: 'kotlin-kapt'
apply plugin: 'dagger.hilt.android.plugin'

dependencies {
    implementation "com.google.dagger:hilt-android:2.40.5"
    kapt "com.google.dagger:hilt-compiler:2.40.5"
    
    implementation "androidx.activity:activity-ktx:1.2.3"//在activity中可以便捷获取ViewModel
    implementation 'androidx.fragment:fragment-ktx:1.3.4'//在fragment中可以便捷获取ViewModel
}
```

```
android {
  ...
  compileOptions {
    sourceCompatibility JavaVersion.VERSION_1_8
    targetCompatibility JavaVersion.VERSION_1_8
  }
}
```

在文档中有写这么一段话：Hilt 使用 Java 8 功能。如需在项目中启用 Java 8，请将以下代码添加到 `app/build.gradle` 文件中。不过目前我创建项目的时候，好像会自动增加这个。

### 开始使用

首先在我们项目的Application中加上@HiltAndroidApp注释

```
@HiltAndroidApp
class MyApp : Application() {
}
```

接下来就可以开始使用依赖注入了。

我们先来总体讲解下，后面会有详细的例子说明。依赖注入的方式有2种，一种就是我们自己创建的类，只要在构造函数中加上@Inject这个标签注释，就可以在我们需要注入的界面通过@Inject注释来调用。第二种就是专门给第三方类使用的，因为第三方的类无法用@Inject标签注释，我们就需要在项目中添加@Module注解块，然后在对应的@Module中将需要用到的提供对象new出来，用@Provides注释来标识出，之后我们就可以在其他注入的界面调用。

Hilt的依赖注入也有作用域的概念，就是你在注入一个对象的时候，已经规定好了，这个对象在哪里可以被调用，然后当这个被调用的区域被销毁了，那作用域中被注入的对象也会自动销毁，是不是很方便。

Hilt目前提供了以下几个作用域，并且对应使用的地方我也一并写出来

Singleton --  SingletonComponent   -> Application   (这个作用域看着有点像单例，但是就是应用全局作用域，我们单例的对象，必须要在这个作用域中注入才能使用)

ActivityRetainedScoped  --  ActivityRetainedComponent  ->跟Activity生命周期有联系的，例如viewmodel或者其他关联的

ActivityScoped  --  ActivityComponent -> Activity

ViewModelScoped -- ViewModelComponent -> ViewModel

FragmentScoped -- FragmentComponent  -> Fragment

ViewScoped -- ViewWithFragmentComponent -> View（从名字上看，应该是自定义view与fragment的生命周期相关）

ViewScoped -- ViewComponent -> View(一般用于自定义view，不过从里面的继承关系来看，应该是view与activity的生命周期相关)

ServiceScoped -- ServiceComponent -> Service

好，所有的作用域都已经写出来了，接下来我针对不同的作用域，来讲解，其实用法都很简单，每个作用域，我都会用2种注入方法来写，代表着自己写的类和第三方类的使用。（其中带有Global字样的bean都可以理解为是第三方类来使用）

### Activity作用域--最基础调用

```
@ActivityScoped
class SimpleData @Inject constructor(){
    init {
        CaesarHitLog.I("SimpleData类的构造函数被调用了")
    }
    fun deal(){
        CaesarHitLog.I("SimpleData调用了方法")
    }
}
```

第一个SimpleData类中，用@ActivityScoped标签注释代表是Activity的作用域，如果什么都不写就代表是全局的作用域，在构造函数中用@Inject标签注入，就完成了一个简单类的注入。

```
class SimpleGlobalData {
    init {
        CaesarHitLog.I("SimpleGlobalData类的构造函数被调用了")
    }
    fun deal(){
        CaesarHitLog.I("SimpleGlobalData调用了方法")
    }
}
```

```
@InstallIn(ActivityComponent::class)
@Module
class MyActModule {
    @Provides
    fun providerSimpleGlobal(): SimpleGlobalData {
        return SimpleGlobalData()
    }
 }
```

第二个可以理解是第三方类的注入，我们创建一个MyActModule，用@Module标签注释，然后再加上@InstallIn(ActivityComponent::class)注解，代表这个作用域是Activity的，最后在里面写上provider的方法，要用@Provides注释，记住方法名不能重复，然后返回你需要的对象就可以了，跟Dagger一毛一样。

如何在Activity中调用呢，也非常简单。

```
@AndroidEntryPoint
class SimpleActivity : AppCompatActivity() {
    @Inject
    lateinit var simpleData1: SimpleData
    @Inject
    lateinit var simpleData2: SimpleData
    @Inject
    lateinit var simpleGlobalData1: SimpleGlobalData
    @Inject
    lateinit var simpleGlobalData2: SimpleGlobalData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        simpleData1.deal()
        CaesarHitLog.I("simpleData1的地址:" + simpleData1.toString())
        CaesarHitLog.I("simpleData2的地址:" + simpleData2.toString())
        simpleGlobalData1.deal()
        CaesarHitLog.I("simpleGlobalData1的地址:" + simpleGlobalData1.toString())
        CaesarHitLog.I("simpleGlobalData2的地址:" + simpleGlobalData2.toString())
    }
}
```

在Activity中加上@AndroidEntryPoint注解，然后用@Inject注解获取对象就可以了，我这边打印了4个对象的地址，都是不同的，说明获取成功，并且每次注入获取都是一个新的对象。

### 全局作用域及单例模式

接着就是全局的使用和单例的使用

```
@Singleton
class SingleData @Inject constructor(){
    init {
        CaesarHitLog.I("SingleData类的构造函数被调用了")
    }
}
```

上面的代码有个@Singleton标签，代表是全局单例的作用域，如果单单想要全局非单例，那就去掉@Singleton作用域就可以了。

```
class SingleGlobalData {
    init {
        CaesarHitLog.I("SingleData类的构造函数被调用了")
    }
}
```

```
@InstallIn(SingletonComponent::class)
@Module
 class MyAppModule {
    @Provides
    @Singleton
    fun providerSingleGlobal(): SingleGlobalData {
        return SingleGlobalData()
    }
 }
```

第二个就是全局的另外一种写法，其他的大同小异，就改了一个作用域SingletonComponent，然后，如果你是要单例的，就要加上@Singleton，如果不是，就去掉即可。如果你去看过官方的文档，就会发现，跟他的不同，因为我这个是最新的版本，官方上的版本还是比较老的，很多的方法和类都变了。最后使用的方法都是一样的，就不多说了。

```
...
@Inject
lateinit var singleData: SingleData
@Inject
lateinit var singleGlobalData: SingleGlobalData

override fun onCreate(savedInstanceState: Bundle?) {
   CaesarHitLog.I("singleData地址:"+singleData)
   CaesarHitLog.I("singleGlobalData地址:"+singleGlobalData)
}
```

### ViewModel中调用

这个作用域是ViewModelScoped及ViewModelComponent，用起来也很简单。

```
@ViewModelScoped
class VMData @Inject constructor(){
    init {
        CaesarHitLog.I("VMData类的构造函数被调用了")
    }
}
```

```
class VMGlobalData {
    init {
        CaesarHitLog.I("VMGlobalData类的构造函数被调用了")
    }
}
```

```
@InstallIn(ViewModelComponent::class)
@Module
 class MyModelModule {
    @Provides
    fun providerVM(): VMGlobalData {
        return VMGlobalData()
    }
}
```

然后在viewmodel中调用如下：

```
@HiltViewModel
class MyViewModel @Inject constructor() :ViewModel() {
    @Inject
    lateinit var vm: VMData
    @Inject
    lateinit var vmg: VMGlobalData
    fun check(){
        CaesarHitLog.I("VMData地址:"+vm)
        CaesarHitLog.I("VMGlobalData:"+vmg)
    }
}
```

这边ViewModel要用@HiltViewModel标签注释，另外要注意，在ViewModel的构造方法中要加一个@Inject标签，不然会报错。还有这边ViewModel官方已经给我们注入过了，所以我们可以用便捷的方式来获取ViewModel。这边要注意，不能在Activity中用@Inject的方式来获取，这种获取的方式会将ViewModel变成一个普通的类，会失去它的生命周期。

```
@AndroidEntryPoint
class MyViewModelActivity : AppCompatActivity() {

//    @Inject
//    lateinit var viewmodel: MyViewModel  错误的获取方式

    //    val viewmodel: MyViewModel by  lazy {
//        ViewModelProvider(this).get(MyViewModel::class.java)
//    }//这个是老的方式
    val viewmodel by viewModels<MyViewModel>()//这个是注入获取的方式
```

当然了，用便捷的方式获取的话还需要另外2个依赖包

```
implementation "androidx.activity:activity-ktx:1.2.3"//在activity中获取
implementation 'androidx.fragment:fragment-ktx:1.3.4'//在fragment中获取
```

### Fragment作用域使用

```
@FragmentScoped
class FragmentData @Inject constructor(){
    init {
        CaesarHitLog.I("FragmentData类的构造函数被调用了")
    }
}
```

```
class FragmentGlobalData {
    init {
        CaesarHitLog.I("FragmentGlobalData类的构造函数被调用了")
    }
}
```

```
@InstallIn(FragmentComponent::class)
@Module
class MyFragmentModule {
    @Provides
    fun providerFrag(): FragmentGlobalData {
        return FragmentGlobalData()
    }
}
```

```
@AndroidEntryPoint
class BlankFragment : Fragment() {
    @Inject
    lateinit var fragData: FragmentData
    @Inject
    lateinit var fragData2: FragmentGlobalData
}
```

在fragment中也超级简单。用FragmentScoped作用域和FragmentComponent，记得在碎片中，要加上@AndroidEntryPoint注释。

### Service作用域使用

```
@ServiceScoped
class ServiceData @Inject constructor(){
    init {
        CaesarHitLog.I("ServiceData类的构造函数被调用了")
    }
}
```

```
class ServiceGlobalData {
    init {
        CaesarHitLog.I("ServiceGlobalData类的构造函数被调用了")
    }
}
```

```
@InstallIn(ServiceComponent::class)
@Module
class MyServiceModule {
    @Provides
    fun providerSerFrag(): ServiceGlobalData {
        return ServiceGlobalData()
    }
}
```

```
@AndroidEntryPoint
class MyService:Service(){
    @Inject
    lateinit var data: ServiceData
    @Inject
    lateinit var data2: ServiceGlobalData
}
```

在Service中，别忘记加上@AndroidEntryPoint注释

### 自定义View作用域

在自定义view中，有时我们也需要获取对象使用。只要指定viewScoped作用域即可

```
@ViewScoped
class CusViewData @Inject constructor(){
    init {
        CaesarHitLog.I("CusViewData类的构造函数被调用了")
    }
}
```

```
class CusViewGlobalData {
    init {
        CaesarHitLog.I("CusViewGlobalData类的构造函数被调用了")
    }
}
```

```
@AndroidEntryPoint
class CusView :View {
    @Inject
    lateinit var data: CusViewData
    @Inject
    lateinit var data2: CusViewGlobalData
}
```

调用跟上面都是大同小异。

### 嵌套使用

我们平常使用的时候，不单单一个类会这么简单，他肯定构造函数中包含了其他的类，那这种情况应该怎么使用，下面也给出2种注入方式

```
class MoreDate  @Inject constructor(val simpleData: SimpleData,@ActivityContext val context: Context){
    init {
        CaesarHitLog.I("MoreDate类的构造函数被调用了,context是否为空:"+(context==null))
    }
}
```

第一种就是自己写的方法，MoreDate的构造函数中，有2个参数，一个是SimpleData，这个类我们在刚才已经注入过了，所以在这边就可以直接使用，系统会自动为我们在构造函数中注入。另外一个是Context上下文，其中Context我们用了@ActivityContext来修饰，Hilt已经为我们注入提供了2种上下文，另外一个是ApplicationContext，我们也能直接使用。接下来我们来看看另外一个注入方法：

```
class MoreGlobalData(val singleData: SingleData, var context: Context){
    init {
        CaesarHitLog.I("MoreGlobalData类的构造函数被调用了,context是否为空:"+(context==null))
    }
}
```

```
@Provides
fun providerMoreGlobal( singleData: SingleData,@ApplicationContext context: Context): MoreGlobalData {
    return MoreGlobalData(singleData,context)
}
```

```
@AndroidEntryPoint
class MoreActivity : AppCompatActivity() {
    @Inject
    lateinit var fragData: MoreDate
    @Inject
    lateinit var fragData2: MoreGlobalData
}
```

在Provides中，MoreGlobalData需要SingleData和Context对象，Hilt会自动为我们找对象，我们不需要去主动赋值，只需要在方法参数中有定义即可。

### 重名的用法

有时候我们要创建2个相同的对象，那我们应该如何去区分他们？

```
class UserNameGlobalBean constructor(val name: String) {
    init {
        CaesarHitLog.I("UserNameBean构造了")
    }
}
```

```
@Named("name1")
@Provides
fun providerUserName1(): UserNameGlobalBean {
    return  UserNameGlobalBean("111")
}
@Named("name2")
@Provides
fun providerUserName2(): UserNameGlobalBean {
    return  UserNameGlobalBean("222")
}
```

```
@AndroidEntryPoint
class NamesActivity : AppCompatActivity() {
    @Inject
    @Named("name1")
    lateinit var nameOne: UserNameGlobalBean
    @Inject
    @Named("name2")
    lateinit var nameTwo: UserNameGlobalBean
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_names)
        CaesarHitLog.I("名字1为:"+nameOne.name)
        CaesarHitLog.I("名字2为:"+nameTwo.name)
    }
}
```

通过加入@Named限定符，就可以指定我们需要对象，调用的时候，也指定一下即可。

### 接口未指明用法

这个跟上面的有点不太一样，先看例子吧。

```
interface ICallBack {
    fun onData()
    fun onDes()
}
```

```
class CallBackImpl @Inject constructor(@ActivityContext var context: Context):ICallBack{
    override fun onData() {
        Toast.makeText(context,"onData调用了",Toast.LENGTH_SHORT).show()
        CaesarHitLog.I("数据1调用额")
    }
    override fun onDes() {
        CaesarHitLog.I("数据2调用额")
    }
}
```

我们有一个接口ICallBack，然后有一个实现该接口的类CallBackImpl，这边注意，这个类的构造函数中要加上@Inject注释。然后接口类的注入也有点不同，因为是抽象的，所以在绑定的时候，类也要是抽象类。然后这边不再是provider标签了，要用Binds标签。

```
@InstallIn(ActivityComponent::class)
@Module
abstract class MyAbsModule {
    @Binds
    abstract fun provideCallback(callback: CallBackImpl):ICallBack

}
```

```
@AndroidEntryPoint
class InterActivity : AppCompatActivity() {
    @Inject
    lateinit var calBack: ICallBack
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inter)
        calBack.onData()
        calBack.onDes()
    }
}
```

在绑定的时候，我们的参数是一个实现类，返回是一个接口。所以最后，我们获取调用的时候，可以用它的接口方法，然后在实现类中，就会自动调用了对应的方法。

### 其他非注入的类中获取

Android应用有4大组件，目前Hilt只支持了其中2个的依赖注入，那剩余2个的组件甚至于其他不直接支持注入的类中难道就不能使用了么，当然可以，接下来我这边再举一个在广播中获取注入的方式，注册广播和发送就简略了，直接上核心代码：

```
class MyBroadReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if ("com.caesar.hit.normal.broad" == intent?.action){
           val singletom =  EntryPointAccessors.fromApplication<MySingleTom>(context!!)
            CaesarHitLog.I("收到了和广播:"+singletom.getSingleData())
        }
    }
    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface MySingleTom {
        fun getSingleData(): SingleData
    }
}
```

这边我们用EntryPoint限定符创建一个入口，在里面定义好你要获取的那个类，当然了，那个类是已经被注入好的。然后我们通过EntryPointAccessors这个类的方法，它有4种获取的方法，对应4种作用域，分别为fromApplication，fromActivity，fromFragment，fromView，获取的类型就是下面定义的接口MySingleTom，然后通过里面的方法，就可以获取到你想要的对象了。通过这种办法，就可以在其他你想要的任何类中来获取注入的对象了。

至此，HIlt基本讲完了它的基础用法。不过我看Hilt每次更新的变动挺大的，目前应该还不是它的完全版本，不过Hilt相对于Dagger是大大简化了难度，还是强烈推荐大家去使用。

转载请标明出处。