part of 'parts_bloc.dart';

@immutable
abstract class PartsEvent extends Equatable{
  const PartsEvent();
  @override
  List<Object?> get props => [];
}

class GetPartsList extends PartsEvent {}
