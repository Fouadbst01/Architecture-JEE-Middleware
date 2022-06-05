import 'package:e_afferai/ui/widgets/search_widget.dart';
import 'package:flutter/material.dart';

import '../widgets/product_item.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Container(
      height: MediaQuery.of(context).size.height,
      width: MediaQuery.of(context).size.width,
      margin: const EdgeInsets.only(left: 15, right: 15),
      child: ListView(
        children: [
          SearchWidget(),
          Row(
            children: [
              Expanded(
                child: Container(
                  margin: const EdgeInsets.only(bottom: 15,top: 10),
                  padding:
                      const EdgeInsets.only(top: 10, bottom: 10, left: 20, right: 20),
                  decoration: BoxDecoration(
                      border: Border.all(color: Colors.orange,width: 2),
                      borderRadius: BorderRadius.circular(15),
                      color: Colors.white),
                  child: Row(
                    children: [
                      Icon(Icons.access_time),
                      SizedBox(
                        width: 10,
                      ),
                      Text(
                        "Engine",
                        style: TextStyle(
                          fontWeight: FontWeight.w500,
                          fontSize: 16,
                        ),
                      ),
                    ],
                  ),
                ),
              ),
              SizedBox(width: 15,),
              Expanded(
                child: Container(
                  margin: const EdgeInsets.only(bottom: 15,top: 10),
                  padding:
                  EdgeInsets.only(top: 10, bottom: 10, left: 20, right: 20),
                  decoration: BoxDecoration(
                      border: Border.all(color: Colors.orange,width: 2),
                      borderRadius: BorderRadius.circular(15),
                      color: Colors.white),
                  child: Row(
                    children: [
                      Icon(Icons.access_time),
                      SizedBox(
                        width: 10,
                      ),
                      Text(
                        "Accessorie",
                        style: TextStyle(
                          fontWeight: FontWeight.w500,
                          fontSize: 16,
                        ),
                      ),
                    ],
                  ),
                ),
              )
            ],
          ),
          Container(
            height: 300,
            width: MediaQuery.of(context).size.width,
            child: ListView.builder(
                scrollDirection: Axis.horizontal,
                padding: EdgeInsets.all(5),
                itemCount: 4,
                itemBuilder: (context, index) {
                  return ProductCard();

                }),
          )
        ],
      ),
    );
  }
}
