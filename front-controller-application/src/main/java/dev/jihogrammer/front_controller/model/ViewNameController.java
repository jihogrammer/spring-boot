package dev.jihogrammer.front_controller.model;

import java.util.Map;

public interface ViewNameController extends Controller {

    String process(Map<String, Object> model);

}
