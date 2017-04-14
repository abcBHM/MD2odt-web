<form method="POST" action="/convert/upload" enctype="multipart/form-data"
      target="_blank" class="form-horizontal">

  <fieldset>
    <legend>Convert</legend>

    <!-- sources -->
    <div class="form-group">
      <label class="col-md-4 control-label input-file-label" for="input">Markdown source</label>
      <div class="col-md-8">
        <input id="input" name="input" class="input-file" type="file">
      </div>
    </div>

    <!-- template -->
    <div class="form-group">
      <label class="col-md-4 control-label input-file-label"
             for="template">ODT template
        <small>(optional)</small>
      </label>
      <div class="col-md-8">
        <input id="template" name="template" class="input-file"
               type="file">
      </div>
    </div>

    <hr>

    <!-- encoding -->
    <div class="form-group">
      <label class="col-md-4 control-label" for="encoding">Encoding</label>
      <div class="col-md-4">
        <select id="encoding" name="encoding" class="form-control">
          <option value="UTF-8">UTF-8</option>
          <option value="UTF-16">UTF-16</option>
          <option value="UTF-16BE">UTF-16BE</option>
          <option value="UTF-16LE">UTF-16LE</option>
          <option value="ISO-8859-1">ISO-8859-1</option>
          <option value="ISO-8859-2">ISO-8859-2</option>
          <option value="Windows-1250">Windows-1250</option>
          <option value="US-ASCII">US-ASCII</option>
        </select>
      </div>
    </div>

    <!-- submit -->
    <div class="form-group">
      <label class="col-md-4 control-label" for="submit"></label>
      <div class="col-md-8">
        <button type="submit" id="submit" name="submit"
                class="btn btn-primary">Submit
        </button>
      </div>
    </div>

  </fieldset>
</form>