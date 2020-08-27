tabledata = jQuery.getJSON("C:\\Users\\Dragana\\Desktop\\PROJEKAT proba2\\proba1\\ispisprofesora\\profesor.json",function(data){

    console.log(data);
    
for(var i in data)
{
    var row = `<tr>
          <td>${data[i].idpredavaca}</td>
          <td>${data[i].ime}</td>
          <td>${data[i].prezime}</td>
          <td>${data[i].username}</td>
          <td>${data[i].password}</td>
          </tr>`

          var table = $('#tabela')

          table.append(row);

}

});