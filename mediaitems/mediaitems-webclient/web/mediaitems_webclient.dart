import 'dart:html';

class MetadataResultView
{
  Element tableBodyElement;
  
  MetadataResultViewModel model;
  
  Element templateRowElement;
  
  MetadataResultView(Element element)
  {
    this.tableBodyElement=element.querySelector("#result_body");
    Element trElement = tableBodyElement.querySelector("tr");
    this.templateRowElement = trElement.clone(true);
    trElement.hidden = true;
  }
  
  bindModel(MetadataResultViewModel model){
    this.model=model;  
  }
  
  update() {
    clearView();
    model.queryResult.forEach((item){
      Element rowElement = templateRowElement.clone(true);
      var nameCell = rowElement.querySelector("#result_item_name");
      nameCell.text=item.name;
      var typeCell = rowElement.querySelector("#result_item_type");
      typeCell.text=item.type;
      this.tableBodyElement.append(rowElement);
  });
  }
  
  clearView() {
    tableBodyElement.children.clear();
  }
}

class MetadataViewController
{
  MetadataResultView view;
  MetadataResultViewModel model;
  
  init(Element content) {
      this.view=new MetadataResultView(content);
      this.model=new MetadataResultViewModel();
      this.view.bindModel(model);
  }
  
  loadElements()
  {
    List<MetadataItem> result=[
    new MetadataItem("Charly und die Schokoladenfabrik","video"),
    new MetadataItem("Der Grinch","video"),
    new MetadataItem("Eine schöne Bescherung","video"),
    new MetadataItem("Der Polarexpress","video"),
    new MetadataItem("Der goldene Kompass","video")
    ];
    model.queryResult=result;
    this.view.update();
  }
  
}

class MetadataResultViewModel
{
    List<MetadataItem> queryResult;
}

class MetadataItem
{
  String name;
  String type;
  
  MetadataItem(String name,String type)
  {
    this.name=name;
    this.type=type;
  }
  
}

void main() {
  MetadataViewController controller=new MetadataViewController();
  controller.init(querySelector("#content"));
  controller.loadElements();
}
