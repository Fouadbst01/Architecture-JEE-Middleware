import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'dart:math';

class OperationView extends StatelessWidget {
  final _controller = ScrollController(keepScrollOffset: true);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: NotificationListener<OverscrollIndicatorNotification>(
          onNotification: (overScrol) {
            overScrol.disallowIndicator();
            return true;
          },
          child: ListView(physics: NeverScrollableScrollPhysics(), children: [
            Container(
              padding:
                  EdgeInsets.only(left: 20, right: 20, top: 15, bottom: 50),
              width: MediaQuery.of(context).size.width,
              height: MediaQuery.of(context).size.height * 0.25,
              decoration: const BoxDecoration(
                gradient: LinearGradient(
                    colors: [Color(0xff01BC5D), Color(0xff008774)],
                    transform: GradientRotation(pi / 4),
                    begin: Alignment.centerLeft),
              ),
            ),
            Transform.translate(
              offset: Offset(0, -30),
              child: Container(
                padding: EdgeInsets.only(top: 20, left: 25, right: 20),
                width: MediaQuery.of(context).size.width,
                height: MediaQuery.of(context).size.height * 0.80,
                decoration: const BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(30),
                        topRight: Radius.circular(30))),
                child: ListView(controller: _controller, children: [
                  Padding(
                    padding: EdgeInsets.only(bottom: 10),
                    child: Text(
                      "Transactions",
                      style: GoogleFonts.montserrat(
                          color: Color(0xff162068),
                          fontSize: 20,
                          fontWeight: FontWeight.w600),
                    ),
                  ),
                  Container(
                    height: MediaQuery.of(context).size.height * 0.7,
                    width: MediaQuery.of(context).size.width,
                    child: ListView.builder(
                      itemCount: 10,
                      itemBuilder: (context, inedx) {
                        return OperationGroup();
                      },
                    ),
                  ),
                ]),
              ),
            ),
          ]),
        ),
      ),
    );
  }
}

Widget OperationGroup() {
  return Column(
    crossAxisAlignment: CrossAxisAlignment.start,
    children: [
      Padding(
        padding: EdgeInsets.only(bottom: 10, top: 10),
        child: Text(
          "Today",
          style: GoogleFonts.montserrat(
              color: Colors.grey, fontWeight: FontWeight.w500, fontSize: 15),
        ),
      ),
      ListView.builder(
        scrollDirection: Axis.vertical,
        shrinkWrap: true,
        physics: NeverScrollableScrollPhysics(),
        //itemExtent: 10,
        itemCount: 2,
        itemBuilder: (context, index) {
          return Operation();
        },
      )
    ],
  );
}

Widget Operation() {
  return Container(
      margin: EdgeInsets.only(bottom: 10),
      padding: EdgeInsets.only(left: 25, right: 25, top: 10, bottom: 10),
      decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(35), color: Color(0xffEBEEF7)),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                "Operatoin name",
                style: GoogleFonts.montserrat(
                    color: Color(0xff162068),
                    fontWeight: FontWeight.w500,
                    fontSize: 16),
              ),
              Text(
                "10:45",
                style: GoogleFonts.montserrat(color: Colors.grey),
              ),
            ],
          ),
          Text(
            "+1000 DH",
            style: GoogleFonts.montserrat(color: Colors.green),
          ),
        ],
      ));
}
