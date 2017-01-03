package com.fortunebrains.nveg.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sree on 1/3/2017.
 */

public class ExpandableListDataPump
{
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();



        List<String> terms = new ArrayList<String>();
        terms.add("Brazil");
        terms.add("Spain");
        terms.add("Germany");
        terms.add("Netherlands");
        terms.add("Italy");

        List<String> privacy = new ArrayList<String>();
        privacy.add("United States");
        privacy.add("Spain");
        privacy.add("Argentina");
        privacy.add("France");
        privacy.add("Russia");

        List<String> delivery = new ArrayList<String>();
        delivery.add("United States");
        delivery.add("Spain");
        delivery.add("Argentina");
        delivery.add("France");
        delivery.add("Russia");

        List<String> refund = new ArrayList<String>();
        refund.add("United States");
        refund.add("Spain");
        refund.add("Argentina");
        refund.add("France");
        refund.add("Russia");

        List<String> product = new ArrayList<String>();
        product.add("United States");
        product.add("Spain");
        product.add("Argentina");
        product.add("France");
        product.add("Russia");

        List<String> help = new ArrayList<String>();
        help.add("United States");
        help.add("Spain");
        help.add("Argentina");
        help.add("France");
        help.add("Russia");

        expandableListDetail.put("Terms of Service", terms);
        expandableListDetail.put("Privacy Policy", privacy);
        expandableListDetail.put("Delivery Policy", delivery);
        expandableListDetail.put("Refund AND Cancelation", refund);
        expandableListDetail.put("Product OR Service", product);
        expandableListDetail.put("Help", help);

        return expandableListDetail;
    }
}
