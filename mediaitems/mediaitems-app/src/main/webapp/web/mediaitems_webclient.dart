import 'dart:html';
import 'dart:convert';

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
    String url="http://localhost:8080/app/metadata/video/list?from="+this.model.start.toString()+"&to="+this.model.pagesize.toString();
    HttpRequest.getString(url).then((String result){
      model.queryResult=this.parseResult(result);
      onElementsLoaded();
    });
  }
  
  List<MetadataItem> parseResult(String json)
  {
    List<MetadataItem> ret=new List(model.pagesize);
    
    List resultList=JSON.decode(json);
    
    int i=0;
    resultList.forEach((Map map){
      ret[i++]=new MetadataItem(map["name"], map["mediaType"]);
    });
    
    return ret;
  }
  
  onElementsLoaded()
  {
    view.update();
  }
}

class MetadataResultViewModel
{
    int start=0;
    int pagesize=20;
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
