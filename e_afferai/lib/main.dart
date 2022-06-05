import 'package:circle_nav_bar/circle_nav_bar.dart';
import 'package:e_afferai/blocs/parts_bloc.dart';
import 'package:e_afferai/ui/pages/home_page.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  PartsBloc _newsBloc = new PartsBloc();
  int pageIndex = 0;
  List<Widget> pages = [
    HomePage(),
    Container(
      height: 100,
      width: 100,
      color: Colors.amberAccent,
    ),
    Container(
      height: 100,
      width: 100,
      color: Colors.red,
    )
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: PreferredSize(
            preferredSize: Size.fromHeight(AppBar().preferredSize.height),
            child: Container(
              margin: EdgeInsets.only(bottom: 15),
              color: Colors.amberAccent,
              child: Container(
                margin: EdgeInsets.only(
                    top: MediaQuery.of(context).viewPadding.top),
                padding: const EdgeInsets.only(top: 10, bottom: 10,left: 20,right: 20),
                color: Colors.amberAccent,
                child: Row(
                  children: [Icon(Icons.menu)],
                ),
              ),
            )),
        bottomNavigationBar: CircleNavBar(
          activeIcons: const [
            Icon(Icons.person, color: Color(0xfff96d21)),
            Icon(Icons.home, color: Color(0xfff96d21)),
            Icon(Icons.favorite, color: Color(0xfff96d21)),
          ],
          inactiveIcons: const [
            Text(
              "Home",
              style: TextStyle(fontWeight: FontWeight.w500),
            ),
            Text(
              "WishList",
              style: TextStyle(fontWeight: FontWeight.w500),
            ),
            Text(
              "Like",
              style: TextStyle(fontWeight: FontWeight.w500),
            ),
          ],
          color: Colors.white,
          height: 60,
          circleWidth: 60,
          initIndex: pageIndex,
          onChanged: (v) {
            setState(() {
              pageIndex = v;
            });
          },
          // tabCurve: ,
          cornerRadius: const BorderRadius.only(
            topLeft: Radius.circular(8),
            topRight: Radius.circular(8),
          ),
          shadowColor: Colors.black26,
          elevation: 10,
        ),
        body: pages[pageIndex]

        /*Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            BlocProvider(create: (_)=> _newsBloc,

              child: BlocBuilder<PartsBloc, PartsState>(
                builder: (context,state){
                  if(state is PartsLoaded) {
                    return Container(
                      height: MediaQuery.of(context).size.height,
                      width: MediaQuery.of(context).size.width,
                      child: ListView.builder(itemCount: state.partsModel.length,itemBuilder:(context,index){
                        return Text(state.partsModel[index].name.toString());
                      }),
                    );
                  }else{
                    return CircularProgressIndicator();
                  }
                },
              ),
            )
            )
          ],
        ),*/ // This trailing comma makes auto-formatting nicer for build methods.
        );
  }
}
