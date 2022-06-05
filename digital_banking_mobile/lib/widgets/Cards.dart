import 'package:flutter/material.dart';
import 'dart:math';
class Cards extends StatelessWidget {
  PageController controller =
      PageController(initialPage: 0, keepPage: true, viewportFraction: 0.85);
  var _selected = 0;

  @override
  Widget build(BuildContext context) {
    return Stack(
      clipBehavior: Clip.none,
      children: [
        Container(
          height: 200,
          width: 450,
          child: StatefulBuilder(
            builder: (ctx, setState) {
              return PageView.builder(
                  itemCount: 4,
                  physics: const BouncingScrollPhysics(
                      parent: AlwaysScrollableScrollPhysics()),
                  onPageChanged: (index) {
                    setState(() {
                      _selected = index;
                    });
                  },
                  controller: controller,
                  itemBuilder: (context, index) {
                    var _scale = _selected == index ? 1.0 : 0.9;
                    return TweenAnimationBuilder<double>(
                      duration: const Duration(milliseconds: 350),
                      curve: Curves.ease,
                      tween: Tween(begin: _scale, end: _scale),
                      child: Cart(),
                      builder: (context, value, child) {
                        return Transform.scale(
                          scale: value,
                          child: child,
                        );
                      },
                    );
                  });
            },
          ),
        ),
        Positioned.fill(

          child: Align(
            alignment: Alignment.centerRight,
            child: InkWell(
              onTap: () {
                //pageController.animateToPage(_position, curve: Curves.decelerate, duration: Duration(milliseconds: 300));
                //controller.animateToPage(_selected, duration: Duration(milliseconds: 350), curve: Curves.decelerate);
              },
              child: Container(
                height: 50,
                width: 50,
                decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(40),
                    color: Color(0xffFEFEFE),
                    boxShadow: [
                      BoxShadow(
                          color: Colors.black54,
                          blurStyle: BlurStyle.outer,
                          blurRadius: 10.0)
                    ]),
                child: Icon(
                  Icons.arrow_forward_ios,
                  color: Color(0xff008774),
                ),
              ),
            ),
          ),
        ),
      ],
    );
  }
}

Widget Cart() {
  return Container(
    decoration: BoxDecoration(
      gradient: LinearGradient(
          colors: [Color(0xff01BC5D), Color(0xff008774)],
          transform: GradientRotation(pi / 4),
          begin: Alignment.centerLeft),
      borderRadius: BorderRadius.circular(20),
    ),
  );
}
