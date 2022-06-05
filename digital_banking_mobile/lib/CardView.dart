import 'package:digital_banking_mobile/widgets/Cards.dart';
import 'package:flutter/material.dart';
import 'dart:math';

import 'package:google_fonts/google_fonts.dart';

class CardView extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: NotificationListener<OverscrollIndicatorNotification>(
          onNotification: (overScrol) {
            overScrol.disallowIndicator();
            return true;
          },
          child: ListView(
          children: [
            Container(
              padding:
              EdgeInsets.only(left: 20, right: 20, top: 15, bottom: 50),
              width: MediaQuery
                  .of(context)
                  .size
                  .width,
              height: MediaQuery
                  .of(context)
                  .size
                  .height * 0.30,
              decoration: const BoxDecoration(
                gradient: LinearGradient(
                    colors: [Color(0xff01BC5D), Color(0xff008774)],
                    transform: GradientRotation(pi / 4),
                    begin: Alignment.centerLeft),
              ),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Row(
                    children: [
                      Icon(
                        Icons.arrow_back_ios,
                        color: Colors.white,
                      )
                    ],
                  ),
                  Column(
                    mainAxisAlignment: MainAxisAlignment.start,
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Align(
                        alignment: Alignment.centerLeft,
                        child: Text(
                          "578.258,5 DH",
                          style: GoogleFonts.montserrat(
                            textStyle: TextStyle(
                                color: Colors.white,
                                fontWeight: FontWeight.w800,
                                fontSize: 30),
                          ),
                        ),
                      ),
                      Text(
                        "your balance",
                        style: GoogleFonts.montserrat(
                          textStyle: TextStyle(
                              color: Colors.white,
                              fontWeight: FontWeight.w400,
                              fontSize: 14),
                        ),
                      ),
                    ],
                  )
                ],
              ),
            ),
            Transform.translate(
              offset: Offset(0, -30),
              child: Container(
                  width: MediaQuery
                      .of(context)
                      .size
                      .width,
                  height: MediaQuery
                      .of(context)
                      .size
                      .height * 0.70,
                  decoration: const BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.only(
                          topLeft: Radius.circular(30),
                          topRight: Radius.circular(30))),
                  child: ListView(
                    children: [
                      Title("Cards"),
                      Cards(),
                      Title("Card Info"),
                      cardInfo(
                          context, "Travel Card", Icons.airplanemode_active),
                      cardInfo(context, "Online Payment", Icons.wifi),
                    ],
                  )),
            ),
          ],
        ),
        ),
      ),);
  }
}

Widget Title(String title) {
  return Padding(
    padding: EdgeInsets.only(left: 25, top: 15, bottom: 20),
    child: Text(title,
        style: GoogleFonts.montserrat(
          textStyle: TextStyle(
              fontSize: 20,
              color: Color(0xff162068),
              fontWeight: FontWeight.w700),
        )),
  );
}

Widget cardInfo(context, String title, IconData icon) {
  return Align(
    alignment: Alignment.centerLeft,
    child: Padding(
      padding: EdgeInsets.only(left: 25, right: 20, bottom: 20),
      child: Container(
          padding: EdgeInsets.only(
              top: 15, bottom: 15, left: 20),
          width: MediaQuery
              .of(context)
              .size
              .width * 0.55,
          decoration: BoxDecoration(
              color: Color(0xffEBEEF7),
              borderRadius: BorderRadius.circular(20)),
          child: Row(
            children: [
              Icon(
                icon,
                color: Color(0xff162068),
              ),
              SizedBox(width: 15,),
              Text(
                title,
                style: GoogleFonts.montserrat(
                  textStyle: TextStyle(
                      fontSize: 15,
                      color: Color(0xff162068),
                      fontWeight: FontWeight.w600),
                ),
              ),
            ],
          )),
    ),
  );
}
