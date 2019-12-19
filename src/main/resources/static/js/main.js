(function() {
  /**
   * マウスオーバーしたときに説明文を出す「ToolTip」を設定する。
   * @param element
   */
  function setTooltip(element) {
    element.each(function() {
      let target = $(this);

      new Tooltip(target, {
        title: target.data("detail")
      });
    });
  }
  function setAction() {
    setTooltip($(".numerical-ability"));
    setTooltip($(".special-ability"));
    setTooltip($(".technical-ability"));

    $(".ajax-modal").hide();
  }
})();
