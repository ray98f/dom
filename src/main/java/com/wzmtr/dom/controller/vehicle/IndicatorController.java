package com.wzmtr.dom.controller.vehicle;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import cn.hutool.core.util.NumberUtil;
import com.wzmtr.dom.config.annotation.CurrUser;
import com.wzmtr.dom.constant.CommonConstants;
import com.wzmtr.dom.constant.ValidationGroup;
import com.wzmtr.dom.dto.req.vehicle.IndicatorReqDTO;
import com.wzmtr.dom.dto.res.traffic.ProductionSummaryResDTO;
import com.wzmtr.dom.dto.res.vehicle.IndicatorResDTO;
import com.wzmtr.dom.entity.BaseIdsEntity;
import com.wzmtr.dom.entity.CurrentLoginUser;
import com.wzmtr.dom.entity.PageReqDTO;
import com.wzmtr.dom.entity.response.DataResponse;
import com.wzmtr.dom.entity.response.PageResponse;
import com.wzmtr.dom.service.vehicle.IndicatorService;
import com.wzmtr.dom.utils.StringUtils;
import com.wzmtr.dom.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 车辆部-重要指标
 *
 * @author zhangxin
 * @version 1.0
 * @date 2024/3/8 16:19
 */
@RestController
@RequestMapping("/vehicle/indicator")
@Api(tags = "车辆部-重要指标")
@Validated
public class IndicatorController {

    @Autowired
    private IndicatorService indicatorService;

    public static void main(String[] args){

        double a = 1.22;
        double b =2.33;
        Double c = NumberUtil.div((a-b), b, 2);
        String dateStr = "2024-01-01";
        Date date1 = DateUtil.parseDate(dateStr);
        int month = DateUtil.month(date1);

        // 获取当前日期
        Date now = DateUtil.date();
        // 获取上个月的第一天
        Date firstDayOfLastMonth = DateUtil.beginOfMonth(DateUtil.offsetMonth(date1, -1));
        // 获取上个月的最后一天
        Date lastDayOfLastMonth = DateUtil.endOfMonth(DateUtil.offsetMonth(date1, -1));

        // 打印结果
        System.out.println("上个月第一天：" + DateUtil.formatDateTime(firstDayOfLastMonth));
        System.out.println("上个月最后一天：" + DateUtil.formatDateTime(lastDayOfLastMonth));

        // 获取当前周的周一
        Date monday = DateUtil.beginOfWeek(date1);

        // 获取当前周的周日
        Date sunday = DateUtil.endOfWeek(date1);
        Date monthStart = DateUtil.beginOfMonth(date1);
        Date monthEnd = DateUtil.endOfMonth(date1);
        System.out.println(DateUtil.today());
        System.out.println(DateUtil.formatDate(sunday));
        System.out.println(DateUtil.formatDate(monthStart));
        System.out.println(DateUtil.formatDate(monthEnd));
        System.out.println(DateUtil.compare(monday,sunday));


        List<ProductionSummaryResDTO> monthList = new ArrayList<>();
        for(int i =0;i<10;i++){
            ProductionSummaryResDTO p = new ProductionSummaryResDTO();
            p.setType1PreCount(i);
            monthList.add(p);
        }



        /*String[] eipTodo = {"9c779b71-4a18-4f5b-8895-130914c7473a4b73d8b1-35cc-45ed-9b0f-b32b66c097b6","87f6695a-1dfe-4bf3-9e75-50ea4742eecc87f09197-b81d-44a4-8fa2-b8f36a0fd9a6","6f9c1097-6685-4692-b5da-bbffc5da2e58a8cadb80-fb36-4495-8aba-a569aaebd83c","5a00b647-9063-4fe3-9a4a-9c42047797a3af5ddfe4-6a73-491d-843a-be8b30aa3442","e067ab04-73ee-40af-a838-29711799d8faabeece22-78d3-4fa6-966c-e053e84ebe23","1f88de70-c8e5-42c6-a161-8a339617c051a6023844-7da3-4c92-859e-3890a8f4a639","10a4254b-705c-4701-a624-d4adaffbd0cb6dfd8ead-38ff-4b1d-bce5-1c65369ff5c0","d0678f7e-0762-47e9-a5a6-f096331ce6139dece148-e835-42e8-9893-91cc44777641","3307f7da-848f-44e1-b72f-e7f5246b8639812ff663-c243-41fd-8d7f-8fc0748b704d","eca70652-daa7-407f-813c-b45babfe018f6b2ee9de-454b-407b-be16-b8281049b88f","05e41cc0-43f2-4ae3-ae00-79f7d9cf070defa63a4d-8ae0-4953-a0e2-b05244cccdda","172f0fa1-8524-4ba8-a080-efa55975b3294acd592f-09f4-455e-9f19-1755b642c8a4","07daed82-c1ac-47c9-b8d8-45bd8382c76e520985fe-5e9d-48b5-8b71-5ba28b363564","44c57911-6abd-4807-98de-4a3befec707971fb6ff6-6eeb-4831-9e8d-170bbab59799","3343c3db-9208-4f35-99d1-497441d28e53f4a37808-5255-462c-9c87-40c2c060cd5e","384f1ad4-aab4-4eab-99f8-cd4641ff63fb905d75c2-43c7-46be-9035-6b681bb34ba1","a45c1199-dd51-41f1-9b3a-4247daea570a47acd5aa-9d7d-4a0a-a332-4ad890e77954","4248d531-1486-48aa-8a47-270d61b31898982664e2-ec0f-477f-b16c-d45ec462f10a","07daed82-c1ac-47c9-b8d8-45bd8382c76ecbb9790a-e76a-4546-a7fd-c4e48a9f908b","ee46468d-b20a-42ba-a8b2-d63f487654556158e11e-caa0-458f-a0fd-cecaf2e566f6","a3469afa-d1d9-42d8-86f8-8b4a0390c1d219955fd2-a4bc-4d8c-a420-ebfc53b682ca","ed9e0e9d-f3e8-4027-9cb6-2359094e924ec78854d5-ca1a-4e87-9b7c-53e0704cf29a","bc9f93b5-bbd5-4789-a7ca-cd25086d0147aa9343f1-3b2f-4e59-a454-b0226be78394","bfb68dcd-b38e-410d-98e8-d17e8b0d84e43c189c6b-9b35-4dbc-81b6-d3491af78960","79843ea0-ce14-4a24-8e9b-e1cc3461c842f9cbe5a6-3f34-4c4f-be33-a3df6c074933","dc250f16-bc0c-4d1e-82ef-54ee2cb449d3ebcef9aa-d6e1-4480-8ff3-c79a8413edfe","73fb961a-70a0-4b84-8a54-4e1d7e0a491cacec2be4-3e9d-430d-a21a-753d0e4899fc","aba74781-b58d-43f4-8c61-1d649415215c8d4afc29-2bd7-4487-b754-3e962438a8d8","d05ad280-8ef6-4284-ae10-c07615a720528184b552-9326-4efd-bdcb-fee42817f0d9","6a2dfdcb-91e6-4d89-a536-a7c33d2ceae3821ef0b0-cf93-410e-a6a8-e156539567b4","bc9f93b5-bbd5-4789-a7ca-cd25086d014720b0984f-87f0-4f83-873d-ad48c94a02a3","a45c1199-dd51-41f1-9b3a-4247daea570ac1c40a9d-c047-4b04-97a7-fa84f89a49ad","e51819c8-eaea-4558-999d-7376f1b4a67a0551e571-fb17-4a2f-b525-cc70f64d6715","ff65058d-2d65-4f63-be26-6d03eb965f4bcd2182c9-4b05-43e9-8beb-ea49c34554c6","4d817a44-951a-457a-ab50-93ad990db7a4613803a5-6b7e-4a77-a1fd-53779c7c3e78","c053f792-46e4-432e-ac68-b2239e2fc9368cea1c38-f4a6-48e4-a977-a30fb36ba863","7db7e4cd-b441-484c-abd2-606eda15b9a36888e194-8a3b-423f-83f0-c273648d6883","f59d4f42-c7b1-4253-ae09-3d2a301a249abd00cc4a-6b8b-44b7-877d-8177e9fc422c","66a1bb57-2631-4506-afef-47592e316e091aacb998-abdf-4551-9e3d-e04d37b968e1","90fcc9ff-4503-4b3d-8ea7-497a6528070d3588e9e1-e19b-4e1e-b99a-a9cb07310d77","57ac4a64-1311-4682-8460-a13a373d1c752f10a831-fc11-46fd-b540-c08b1716b8df","208f0194-c447-4975-bdf8-9d1e39f738db8341b8fa-d18c-402d-ad57-1e2061caca30","adf49c3f-ca76-4194-a5bf-5f7fd50fb087278f5273-3fa5-401c-9328-83bac14e03ad","6159679d-f7f4-4385-ac45-81b704a8e4f3f873cdb0-57cf-4c57-a504-84013e4bd911","376a6923-3239-40c4-9ad6-14d9523c78e067cccda3-4daf-4d76-a4f2-a1985dcf0713","dc387bde-ee64-4a5a-805b-429887218c6c42c42f75-8f94-4289-b385-e2eb756fc27a","59212255-d6af-4d0a-83de-f49c59869f6a7618283c-d8fb-40df-a167-c9713701c914","3740c5ed-bb23-461e-aa7a-20817e5829698cd03fe8-73d9-4592-bb53-179560dccced","e7160f19-1210-4b56-b99c-0c05673d22e027327e34-9d6b-4ea0-a28c-e6fc3cc7e675","7e4ed036-72d0-4e27-928a-9ebb54c216bff45e198c-e2dd-4a8c-b554-85b40fb7c7db","acdd4eaf-f2ec-41c3-929c-cb730154bf337d99bf0d-e3c7-4884-bed8-4b4905e3ebf0","23510c12-4187-4a3c-ac2b-9a0e68a28367ad4ecab0-d7bd-4af0-9ed1-263891074b27","6134ec95-4550-4b10-bed5-8913e33484ee7f8fa963-0cba-4d3c-8ae4-1ac67da63740","4aa87391-5670-4bdc-9c45-5b4804c88ed169cf7e7d-5a4a-41d3-9e68-45abdc29bb80","70a27642-ae9a-4681-8e16-9909845c416b2c02e785-d49e-45ad-8aa0-d7f78c3029db","e0ad4ce1-34c6-4385-8163-0f9885f833241f567376-55cd-465f-8248-7b529b32f239","76e066e5-8a80-40d9-a622-c763f931e21f96303bce-14c0-43cd-804c-6c09b5e39eb5","07199b77-e4bb-4a75-b807-3b5bb2f39bac19139aaa-fc2d-4d93-8535-4e4dd79fbbf8","05e41cc0-43f2-4ae3-ae00-79f7d9cf070d7f274823-4304-45c8-b440-0c699e759fd1","5493776b-c188-4a65-88d7-522ccef2d6b056ecf348-8481-4142-8273-d05db1e288c0","985d5abb-cfaa-410e-a20a-6925113c8284f6cd61d3-060a-4cdd-a9d2-62045f8769c0","ae20906a-5169-461e-bd92-189689ff1a9ba4df590f-9a56-4b34-8f8a-c6d222c0de67","6b5f8bad-1f7c-46ec-bab3-b3bee1d3b3ed7c3d2d12-5cf5-4422-affb-8a24d87d24b6","37ce6123-4701-4187-a240-bc6448492e03905d188f-7cf5-4b53-be24-871e5459214d","3026ad43-7126-45f5-bff6-7bfceb263f01b3e73d88-df57-4cda-92ad-cc723b60209b","e76eefe7-bb75-4d87-abdc-a699b631a584faa2d919-46d9-4022-811e-b1a64dcc09b1","4c406f81-b794-4d97-b024-dd455f9fcf360fee1fb4-530d-47e1-9bd6-daebf4498ceb","9637c3ba-8a22-4653-b64b-d0b1eab501aa1e0d082d-d087-41d3-9bf3-bc3f86d862d2","1964cb9f-4ae1-4153-9f7e-53c31b4a8b2e39416849-6879-47f0-b5fe-5bf550379f30","e06208e0-6129-49c4-8aa0-331d8dab3ec5007e84fd-0491-4006-ba62-d0f522935d48","02177b57-05f2-4d80-bb4a-41a5811c77e79ce7fb48-114e-41ca-89c4-81639547536b","130ca2f5-8b00-4272-8991-541ab4d76a3c0e038746-96db-4651-a2a6-172068faa73e","4d817a44-951a-457a-ab50-93ad990db7a44d46c7a5-ee26-49fb-bbbd-36d33241bd46","69529fca-304d-451c-9140-38a6883f61bf75cd1b20-90ce-47b7-bc3e-a03c7bbdd4d1","8bdcea14-c99f-49d0-a571-0429aede75cba9fca2ac-0f0e-4bbd-b033-30f5c7939767","1ead0876-24af-458f-964a-bdd053046fbb9cf06cd4-0593-4633-8665-941a4abf438c","72d45cf2-6a78-4d14-b0b6-c8d28852f464bf9ab25d-19c5-41ff-ae74-2d143874d941","e06208e0-6129-49c4-8aa0-331d8dab3ec5300c7437-37bb-4fda-a2b1-6a8cd240d7a6","130ca2f5-8b00-4272-8991-541ab4d76a3cb46d487a-06ed-4962-87a7-897feec2f0c9","2c50a45c-c06b-4f02-84b9-7aee33ea598b5fe99bef-5b23-479c-91c5-a3b41307c4d0","3a9f6699-7e6e-4ff1-b868-0849d0e4309aab2ab5a8-9707-4583-abec-819ae4c14847","5fac1fb2-17a9-45a9-964f-1c4d4c12966155775687-ee7d-47ad-b90c-a3e6f50d33d9","7ddd8e92-dfb5-4bc2-b108-c349e4eee4c4b0907888-7a8a-4c1e-b91d-96ae6872f7c6","ea9f89fb-fdba-41d4-a93b-b2732cb577afc77e2760-a717-46eb-9450-e160c250c9bf","9842d317-ed20-476e-bc42-581fb95572b58c9fb7b5-664d-466d-8dfd-afbf31c3c004","b419ee72-7cdc-4d16-8ee2-683dcd195aba1b6ab769-d84d-48c5-aaf4-ab9f73e10df2","f59c7a0c-6e97-47d7-840a-7ead6fe92256dc875a4c-3e63-4f66-ad7b-d3a39b17d76e","4dd3e638-1f5c-451a-8d53-160d96ba4a4eb4a5bd9b-ba86-464d-ad84-10b2df6a45ae","ad891ca6-6c34-4afe-80e9-8286f4239a6871dab739-0d9e-48a6-833d-2665870ac57d","112bff3b-2a4e-4cfa-8775-e74265f3d0b502dc2f1f-e7df-4de8-9eea-ee4caa3eb606","f359004b-c26c-42a0-a4c3-bda6e695fd4048f7d38b-04c4-40cf-a9a9-31fe1f960fe1","5b586b5c-3a0a-4b98-a6c8-0b8d2de60dc71e7ed492-1229-4d7f-a316-51c5f82264d5","b7ad93c7-6e69-4d63-95c4-6947129a8334065344e3-dadd-4f01-8ec3-6e83ebfd9b12","ff8e91db-385b-4810-84cc-f5a67d324c36b461622e-f8d9-4edd-9eff-0a9d4455c669","4aa87391-5670-4bdc-9c45-5b4804c88ed125362468-5d59-42cc-98a7-3299793fa63d","cdb641bf-f662-4047-9983-c820ff4b8119656b0db8-46b6-4e59-8188-f884a0f9cb0f","31739ea2-3370-43f7-ad7f-7485d9489e2201f20ff8-e344-43c7-b03d-cbd92c0c2d75","cdb641bf-f662-4047-9983-c820ff4b8119aa9f6765-6988-4577-bc92-593b4f515f15","657cf96d-c14f-4c8b-8823-9c749ed66ee66b6379da-2c72-47f2-92f5-f90ebf308d06","a51f6ea0-52f9-494d-a158-725e8862006864c08c62-8f5d-4a5c-8936-63f9959d324f","c10d721f-ab7a-443c-ac91-abec3c0380f6daa8fec2-79bd-432c-bcc7-34a783f30b64","642f8e34-2489-4241-ae63-ae5faadb8b00fd84e1aa-d648-4d60-b34e-6e2aa61618e9","4ac1c913-cbba-4bd5-980a-e904c04f8cb9e9848656-7b32-493d-8654-abe748cb4507","1cf63680-0994-4a61-96db-a89c6d04ee27be6c8f2e-fae5-4acd-8b20-1e379e1b7b87","30d3c1a8-6572-4e25-9979-b8a7f9658c488c871867-8747-42c6-b853-6b751d8a0105","524512a2-4aaa-48ae-a9bc-2e6f3e48a9d1c658255c-7dab-4348-9eb1-fef36d299ba8","5b4aa374-280a-41f9-9f08-7aa2086bef8177f930e6-6dcf-4489-9f7d-bc90cb24bd13","b5542165-cfaa-4242-b7e2-5cefd872031a45726b3b-505c-46df-969d-166182e4a83e","4c46cec6-c20a-4674-89b0-5ab67ad1cfc51fb033b9-eebb-48d3-b4b8-502b6b46ca15","7d6ee8fa-3ebe-4f2f-8d5e-f956fee7e46102ebee29-9ccc-45d4-b630-a85f628abc01","981366a7-48bb-45d7-bb5c-a6d516ebb3d5c1f551a3-dccf-4412-9b0b-412b7c43ac88","4248d531-1486-48aa-8a47-270d61b3189805b5b08f-4712-4173-b68e-d0d39d443ab0","e067ab04-73ee-40af-a838-29711799d8fa9b8018e0-3ceb-4280-be11-3283a13d799e","fbe1c632-5a38-498b-a2f0-93002a03fd30df9a6f32-7399-4031-810d-35c23f99ee40","70a27642-ae9a-4681-8e16-9909845c416b8a7fe984-2a86-42ef-88f0-0f9a349a377b","5149eea1-4987-4768-8ef1-6ff20fde676299b7c3d1-d3fd-436b-bf18-504625fa6dfc","c053f792-46e4-432e-ac68-b2239e2fc936295baf74-fa84-4853-9bb9-eed2e9cdc38d","dc387bde-ee64-4a5a-805b-429887218c6c8c14a2ff-c7cd-4e9d-ace9-321ba0e49e50","36c4d43c-6665-4b11-80b8-568d0aeb1e168b371887-aef3-4b6c-8d34-60f03e6a0490","69529fca-304d-451c-9140-38a6883f61bf5fe3834e-1bb2-4e13-84a1-2e21ad2cdb3f","63ce2645-d061-4060-bc63-cd89b549f7300c91d346-f1f1-43d6-add0-5281379d0127","a8da187a-0a48-458b-9a53-8f8abdc3bfb4f54f65ab-5a8b-4042-8596-3167c84707f6","d2887ff6-f488-4bc2-9a76-74a322223a6055ffb7c1-ba51-49be-b2c3-079ba5a8f2ab","9637c3ba-8a22-4653-b64b-d0b1eab501aa183167db-0a63-4eca-a553-9caf3fe17e41","ecf23d13-4ea9-41b0-a80b-5ee66313633bc0f47bf5-412b-447a-8b67-132050d9df6d","7098b606-dc7c-4802-9333-c4f8647d6a09fc593de5-6d50-49fd-9826-8b1f41081c44","a8da187a-0a48-458b-9a53-8f8abdc3bfb4069fb5a0-7686-4738-baef-ecea0555874b","c1004a5c-2d93-490b-9a5a-56dfaf464a14984893e5-8161-4ab5-81b9-2ce99a8037b1","c1004a5c-2d93-490b-9a5a-56dfaf464a140fac4404-d5e2-47b0-82ad-1522bd24341d","fdc94b60-bac8-4bbc-8e28-008f2d249f1ec0a1529e-3d8f-48f1-999c-24c48daff66d","acdd4eaf-f2ec-41c3-929c-cb730154bf334b83dce6-31c4-43ac-a575-654597ce3066","bf45583e-0dfa-4735-bd8a-38f7b0c62009a8a0d507-dcbd-4653-a042-c7c26e191565","f59c7a0c-6e97-47d7-840a-7ead6fe92256a064d2c0-18de-4ce0-b7a5-21a87c068596","57ac4a64-1311-4682-8460-a13a373d1c75f0bda999-a509-40e4-b1a4-c1982104dca1","208f0194-c447-4975-bdf8-9d1e39f738db87cbbe8f-a615-49d0-b3c7-3f09ea0b5267","59212255-d6af-4d0a-83de-f49c59869f6a9029357a-2fbc-477d-97b2-392d888e8374","85fc1db4-9fa5-4267-b746-7cd0dd98257e5aa7b897-bc0e-4ef1-879f-319f70eac739","d03586e9-043d-4ada-8e1f-f8696d0ef43f44b08a59-4c51-4b36-8c1a-e6edf2b31e61","cb91468f-d566-4325-badd-f7550a42632cd74ff986-b32e-4d2c-9e2a-e90011693457","361a6b01-cb1b-45f1-ab06-987881ba2fc3c75f194f-024f-4b3f-a714-de3af0ec5798","4637755e-96f0-4b63-8811-57ce0aa751173ed0ffd7-48a5-4a24-8c09-75472c642551","07ccb6b7-16b8-4c69-bf8b-707b95f49c6461c3f1a8-d541-4e3c-b6bc-9af0bae7b7c4","48f58193-cd32-43ed-9615-cb732727238c65244994-2175-445e-ab9c-7cd8d05410be","f4ffbe06-24d5-4944-bc07-7aef61a176cc53870e2c-1146-4faa-a535-6d2f3a6a95a0","4da4bd65-5e67-44d6-95fa-c08b2340e07b3d62da2c-99c1-4c3c-89df-403c65e05f50","1cf63680-0994-4a61-96db-a89c6d04ee27f778c5c7-38d1-4eaf-9447-e8848045a9c3","6a2dfdcb-91e6-4d89-a536-a7c33d2ceae30b38f89e-74ea-4709-8bf6-08d3b4f455c6","2e3332f8-abdb-478b-b6a8-6af1457b3d4461d1a8a4-efaf-48e3-9769-568c8ada32fe","46aecda3-ee1b-49e1-b240-732300fa9fbbfd779f58-380e-4c48-94ca-5165d9ad4b27","93d26cd3-53f4-4023-bf31-86499488b3772e5845dc-67f5-4f25-8caa-cde546191724","2d5f613b-f307-4e4c-a4d1-3feed2dc3418f79e9774-2e2e-43d5-9e03-d7b656d762bb","4aa97f79-2032-4f57-9aee-307aeddff1de714c92d1-ee14-4d3b-9a58-f902ea1c4f4c","553a8387-5db8-47ba-8580-3d5ea574ebb4ef89cf53-c125-4277-a699-550e5a9a0eb7","3667f12b-67cc-4b6e-9b20-e7887aa860a779be9c01-9a37-4026-969e-0532c279ff85","93d26cd3-53f4-4023-bf31-86499488b377a3d60aa9-bc28-426d-8d94-e8a7a1ed0434","1ef78624-a653-4f20-9041-67f25959328d96653d71-ec38-4ce7-b4fc-1b8125d301d8","845ea8b9-d109-4839-80cf-aba20350e75228350d3d-71f8-4809-8d76-06a892bcf936","52ce3321-5949-4d7b-9651-70743edbfacce71cadb2-a0cb-44d5-bc4e-6f9fef459b63","7605d811-5377-4fde-8854-6f268bb3d7754f5b0d0e-24ab-4b4d-a2b3-b8053a353ac1","4d817a44-951a-457a-ab50-93ad990db7a46d3b7846-805e-4a97-a247-2d67d8d3ca68","c8e33133-67e9-44d9-a3cc-6189c17add7ecc3eac9b-435f-4ae7-b55a-0ab49c9cd7bf","25ee90e6-3806-4b4a-a46d-51ce306bc7a3a247b5f6-d497-4dc5-b1f7-680e0c65860e","acdd4eaf-f2ec-41c3-929c-cb730154bf333079f370-d303-4adf-9792-14d869af2103","cd2a0af9-bbe7-45f5-97d3-399a36b76e49d9dbcf37-44ca-48d3-915d-189dab335417","c64dfc46-c39e-4773-b645-641f3937ccb85bc605bc-2d84-44cc-ba9a-56c81575c859","d476048c-84d4-4b48-86c5-5740fcc1f4f1c3e40fc5-058c-4c0b-a205-6ee20cca2771","fbb57dd7-bb53-4090-8ecc-56f76892d263a6eec40f-f8de-4852-92c0-33754ffe10b1","eeb47967-c3be-422d-af41-2f0ad2684b894d7feaa7-4519-4f14-85f7-dafb302d5f63","985d5abb-cfaa-410e-a20a-6925113c82840c521664-afb4-4393-8925-c9698bbbc7a3","9ac01715-f9a4-461a-872b-d1f381f7252540fe03ec-b00e-46c3-80d2-17318624a7b4","6bf3c106-61f8-4843-a4f8-62313191a93c55cafb4e-9f04-497f-b066-10a6ef5e5a61","ea9f89fb-fdba-41d4-a93b-b2732cb577affcc50ba1-b651-4fdf-ac34-b1838a227486","ff65058d-2d65-4f63-be26-6d03eb965f4bd5d2aa8d-270d-469f-82e6-ec0f45024ebc","384f1ad4-aab4-4eab-99f8-cd4641ff63fba3804721-5312-412b-a390-df0e741df5a2","4ad1ef74-c57e-464d-a756-a6da6587b4f3155122d1-85b2-413b-9741-4c80a67ae878","30d3c1a8-6572-4e25-9979-b8a7f9658c4821c51f25-7b47-4fea-adcd-fb906f526934","30d3c1a8-6572-4e25-9979-b8a7f9658c486994a0df-9015-4c46-a215-134a3b8484ae","9842d317-ed20-476e-bc42-581fb95572b5a74d5fb0-d1cc-40c7-861f-4006cc49f626","aa553902-0ccd-4180-97c5-c305b955a167820d899b-788e-4e22-b35a-c174249fdedf","a6ddcfb2-0d8d-4b34-88f6-de9fdadbd27387190de1-9cd4-46ae-8fa7-43a29ff90e1b","87f6695a-1dfe-4bf3-9e75-50ea4742eecc62106b56-08a8-4038-84ff-e5e7a8c38229","f359004b-c26c-42a0-a4c3-bda6e695fd40534919f9-8c07-42a0-8482-24586ace1ada","7ddd8e92-dfb5-4bc2-b108-c349e4eee4c4993867a9-3340-4b40-97ad-0ef7f245d8d9","a3469afa-d1d9-42d8-86f8-8b4a0390c1d2d92c0f4b-0f11-4af5-8f40-4d12343d4c3f","172f0fa1-8524-4ba8-a080-efa55975b329a65f24b8-52e7-4f2a-bad3-b3f7c92fc6dd","937257ad-f7cc-41e5-944f-93333828af2c6ec1c65d-993c-4e18-9240-a01a76ddea89","bf6d8fa1-2382-44f9-9d61-afc31c52574ffae28625-1bac-4d14-b272-6945c74597cd","31739ea2-3370-43f7-ad7f-7485d9489e2281998d37-4089-431c-8db3-c5f2e31947ce","cdb641bf-f662-4047-9983-c820ff4b811941aa486f-a407-4ecb-9faf-fd483b13e554","e79cc586-1962-48ac-9d83-330d90d4794f0491acd6-b36b-45a2-b300-ab82229564bf","9ac01715-f9a4-461a-872b-d1f381f7252596979df4-a399-4037-9576-ecee77e82db1","d05ad280-8ef6-4284-ae10-c07615a720529f66ea11-f7f5-482c-bc14-ea35859043e4","c255314b-421f-4cda-9172-74e6b7aacca7f02b5cdf-7e54-4b63-ab13-e453982ff3a0","46aecda3-ee1b-49e1-b240-732300fa9fbbc3646c14-821b-4f9c-b8a8-7bc9378130b9","16d7af21-c1d1-4abf-9a56-56aea2bcf7f85d385016-263b-4739-a15e-b228cca1853e","3343c3db-9208-4f35-99d1-497441d28e53a2cabd8a-70e3-43a3-8d66-8feddf793fa8","e99f5081-61d4-4ba7-9b37-5963cff3762d12fd9b1c-8f3e-4144-8edf-a8ccf5406a48","16d7af21-c1d1-4abf-9a56-56aea2bcf7f8fc071196-6e84-48f6-bc3d-3d5e6aa128eb","9e6c60a0-b592-4a23-874f-9a33a0025cf9463e3baa-6095-468a-84d6-b419b545c8e2","24d0dda1-57b6-47a7-bc30-4a7eab9befb368931763-974d-41b6-b47d-1855d7810da6","2dc102a6-86c4-442e-86a9-ecd1a43a0cccce1b8ffa-388b-40e8-b67a-9c5205db37a4","5341d020-692d-497a-89bc-4082e4ae0879566fe0a4-a7ea-4038-9e3f-d14773f55806","30d3c1a8-6572-4e25-9979-b8a7f9658c48b0953691-7fa5-48f8-ae2c-006f67d09f9f","efcded25-a212-4183-9bc6-0ab9d702c55771604365-ec9a-4b1d-95bd-791275ba9e62","44e4d632-d7f1-476b-8d27-4441cf63fda7054ffffe-c52c-47aa-8dd8-376a9103debb","954537c6-736f-404e-8b10-26e6a4e5c7197fcf70fd-9956-4448-a878-0ca6fdf886ad","75acaddc-0c61-461a-b8d9-29f03ef330519f07187f-1edb-42c3-8e4e-34893e62e46d","82d18629-86f6-4430-bd42-5445437c8bbc33e9ab4c-d043-4f96-bc5a-bf0d0651397b","a081006c-4410-47b3-8221-441bff1bf391500d2456-c301-4391-b3b7-ccbc2baa6d8a","05caad90-77f2-424b-a838-93851f265af7cde1decd-ceb0-434c-a361-a9d36ddc711f","f3120301-6c0c-42e0-9b52-03f848307abcf2e366cc-091f-490d-97d8-ae4fe3649f00","ee2bae64-2988-4ef3-812a-47d1c61b3fb54223d453-a304-47f5-ab58-353445743d1c","8970c558-00d9-411d-9558-d65e0e39fb90224a14ab-2bc2-483a-88b9-0f96915f2372","4ac1c913-cbba-4bd5-980a-e904c04f8cb91c06e456-2dc4-486d-9f45-9abcb9a87433","fbe1c632-5a38-498b-a2f0-93002a03fd30fad661dc-1d21-4b7e-bcb2-3dbe5d7570ff","c2f970fb-3f7b-43e2-bf9c-bdd16d1d6f97cb58124f-6935-4b8d-9af3-ae9cc3b1f34c","7a7ce941-2e4b-46f6-bc24-5188de17916050217c58-cb0b-4f87-93af-70ab956b9564","02177b57-05f2-4d80-bb4a-41a5811c77e78a1a9737-f620-4bdf-ae7e-23d794eea260","393a7037-4524-4075-8c8a-b2367291c08c5fee9cac-52e1-4e55-a216-7e5fd7720b37","44c57911-6abd-4807-98de-4a3befec707957ea22d2-ca93-4706-96c1-3fc4a7a98b12","07daed82-c1ac-47c9-b8d8-45bd8382c76e94ea64eb-3705-4b8d-b6ce-5df826bb7423"};
        String[] hrTodo = {"dc387bde-ee64-4a5a-805b-429887218c6c8c14a2ff-c7cd-4e9d-ace9-321ba0e49e50","eca70652-daa7-407f-813c-b45babfe018fea7c48d9-3f91-4c8d-bdbc-1f46a3eb4b71","91b892b7-97ee-4983-a15f-f4f795367ad7de771aac-258f-4679-8940-01324dc4abb9","e82e7bf1-74fd-44e0-9a15-0d78eab753c3ed17a100-e803-4dc6-ad5e-ae1930f65d77","3bbcb0f7-f4aa-41d0-953f-2234febfb0ce422a292a-716b-4552-ad31-a7064e2dfe99","cdc48c1b-9dc0-480a-9e3a-3a5ee3c70020ea6ab366-8dd9-4d38-834d-1a7e4e1e695e","489f0c91-6170-48df-ab9b-79de0917220787973c0d-d1c2-49df-8ef5-7455130bec2d","a10d3ddc-ec3f-4cec-82b5-35f391d0a9a8d1b0ee24-661a-4ae3-a17e-822600cab0d1","8b7ec505-e0b8-466a-9c52-2c19d6bbf9201e838a02-f847-4d0c-947b-03ca9262dccf","ad843bd6-6491-4832-aae8-f8a891fa57f49608ef8a-d002-4718-9a9c-e41c470bf22b","a0b6dae0-71cc-4dea-9deb-75a29fd895161b037a0e-7ab9-4972-9651-39cd370089ca","1ad0c7d2-03aa-41c7-ac7e-3aa632ad7b88b7bf8f9e-5dd5-4852-a0f2-fba00758d03f","ff65058d-2d65-4f63-be26-6d03eb965f4bd5d2aa8d-270d-469f-82e6-ec0f45024ebc","d03586e9-043d-4ada-8e1f-f8696d0ef43f44b08a59-4c51-4b36-8c1a-e6edf2b31e61","da124a3c-a7a7-4bad-830c-39a7358b88b9f4a84c42-9334-4fd1-b9b6-b6e180bb0a15","a8bc3fb2-0f1f-4f94-9ee5-9017fdccb434a88f1db4-d522-4220-89f7-435e7171b7bb","da9ad876-af7e-4436-98cf-eee429f44a90d530765a-08c0-4be7-8c73-e458ffa3e420","6a5dc0ab-5fd0-493d-b4e8-90ccbb13b354132dab04-7d18-4bf6-b7bf-0df2ab74199e","5c2d0bc1-c915-47b5-93de-a7846ddf75cd4a1c16f9-31e3-46da-96bc-11f0ede3b47d","c38cff40-30b7-42cc-87f0-6d1bf8acb1226bb7917b-24ef-4350-925d-ad04cf33f756","bf45583e-0dfa-4735-bd8a-38f7b0c6200943108c2f-abcd-494b-af77-4b7e3f8852eb","ae20906a-5169-461e-bd92-189689ff1a9b59f97fed-f1ad-46e7-98a5-5d63c44577f8","db25351d-7473-418b-9780-ad00f2b1eed8d35de0fa-9361-45c0-a707-6c465846c0dc","985d5abb-cfaa-410e-a20a-6925113c82840c521664-afb4-4393-8925-c9698bbbc7a3","05e41cc0-43f2-4ae3-ae00-79f7d9cf070defa63a4d-8ae0-4953-a0e2-b05244cccdda","ed9e0e9d-f3e8-4027-9cb6-2359094e924ec78854d5-ca1a-4e87-9b7c-53e0704cf29a","bd187ffa-c284-496b-af48-bb1d8168d10d4279e91c-aa49-49db-beaf-b6e4cfc78851","f62ed1ba-151e-4fa7-9743-1f92efc1d18df4680320-c63b-4cd2-995b-fd357ef86313","70a27642-ae9a-4681-8e16-9909845c416b8a7fe984-2a86-42ef-88f0-0f9a349a377b","59212255-d6af-4d0a-83de-f49c59869f6a9029357a-2fbc-477d-97b2-392d888e8374","8970c558-00d9-411d-9558-d65e0e39fb90224a14ab-2bc2-483a-88b9-0f96915f2372","208f0194-c447-4975-bdf8-9d1e39f738db87cbbe8f-a615-49d0-b3c7-3f09ea0b5267","57ac4a64-1311-4682-8460-a13a373d1c75f0bda999-a509-40e4-b1a4-c1982104dca1","f59c7a0c-6e97-47d7-840a-7ead6fe92256a064d2c0-18de-4ce0-b7a5-21a87c068596","e06208e0-6129-49c4-8aa0-331d8dab3ec5007e84fd-0491-4006-ba62-d0f522935d48","69529fca-304d-451c-9140-38a6883f61bf5fe3834e-1bb2-4e13-84a1-2e21ad2cdb3f","130ca2f5-8b00-4272-8991-541ab4d76a3cb46d487a-06ed-4962-87a7-897feec2f0c9","4d817a44-951a-457a-ab50-93ad990db7a4613803a5-6b7e-4a77-a1fd-53779c7c3e78","07daed82-c1ac-47c9-b8d8-45bd8382c76ecbb9790a-e76a-4546-a7fd-c4e48a9f908b","b63f5e5b-8e95-4d9d-9e82-983b0606b21b8da73d74-812b-4b1c-b271-f519b845d60a","00f86f71-6140-4373-88dd-f8e5400cc7d0b626c6ba-516f-409b-b956-fb3805f4f94a","79843ea0-ce14-4a24-8e9b-e1cc3461c842f9cbe5a6-3f34-4c4f-be33-a3df6c074933","bc9f93b5-bbd5-4789-a7ca-cd25086d014720b0984f-87f0-4f83-873d-ad48c94a02a3","5fac1fb2-17a9-45a9-964f-1c4d4c12966155775687-ee7d-47ad-b90c-a3e6f50d33d9","dc250f16-bc0c-4d1e-82ef-54ee2cb449d3ebcef9aa-d6e1-4480-8ff3-c79a8413edfe","4aa87391-5670-4bdc-9c45-5b4804c88ed125362468-5d59-42cc-98a7-3299793fa63d","f59d4f42-c7b1-4253-ae09-3d2a301a249abd00cc4a-6b8b-44b7-877d-8177e9fc422c","9a63378b-e537-46cf-bbf0-c479236c6400be484c38-3646-49de-b7de-0d45adc084fd","cb8400c2-34b0-4ce6-b9a2-94af42c3e442d00dea1b-4d16-4729-898b-b4e00c767f4d","e9eec4df-92f5-48d2-a5f9-c24b8d54cf99876fe838-577c-4dd1-879e-8dd9a44b89d8","46f784b1-d550-41f1-be46-7b922e5cd6e408a1b78e-03e1-4a63-b4a4-1efd31a184b5","ab84aefd-13b4-43d0-ad9c-8132a88dd698d9217502-71e7-40c4-bb68-1d77d01395e4","3d18d211-c503-4365-9576-a34d29c3cb6efcfe01c5-3907-44a2-8568-8e65d654283d","1ab9c924-df91-4057-91f0-21940ef2595d4f91f96d-8dbc-484c-8304-786c6fb63096","e9c42c11-93bf-42d5-abcb-3a3b91a767b068d8bad4-c858-499b-84e7-03bcfb8de8cd","bfb68dcd-b38e-410d-98e8-d17e8b0d84e4486d0367-23eb-4f4a-8e4a-68415cfdcddb","4cf70fd6-087b-405c-af2f-ca608662f7ab09137354-91f3-4b92-87e3-4eec78d0026a","366c53e6-86bf-4cc7-adf0-33fd087ec919f4441ce0-21e5-40c5-9b1c-8cee23f2ca30","3cc6fad7-608a-456a-8ad6-02872ed8eba79baeed1d-2e7f-4bce-b402-35010db73534","24c97bbb-9f11-4dc0-989e-9b4996f26aaa46d658a0-6f58-4ad0-9c1b-e49a40f86185","c3deb642-95a2-40e5-912e-83131adb564c4b13c761-24f3-468e-86c0-4051540cecde","0876d503-aeda-46b1-a709-df04615e62f748d88268-4270-4431-aa52-eee023538c87","6acda607-c2ae-484c-9d3a-b4ae832bbf1895424378-e037-4103-b8e9-788caa20cebd","ac53f159-23ff-4cae-b59b-a9d746586225bdaf4710-5ec0-457f-bfcd-1cc78e282d1b","48dd1c78-a273-455e-b01f-4247b40aebe64ceab177-23f0-442e-9901-79b065acd079","6f85cf62-36d6-45fc-8177-0fa135ac3d153d8f0438-ac98-4992-bb4a-cb83352b99f3","19cfaf8f-5a50-4b24-8805-77bdcc4e5cd4543ab50a-9c04-4536-a33f-5068ea4c1b7c","453e4bde-682c-472f-897a-c666f9d2439ee33422fb-f851-4f80-8cce-8f4f8585083a","be37898d-4e9b-4da5-8e82-431823fa2e6a9c2130a2-c0d9-4d1f-9835-14fffb5c9ac2","4c627ca2-a4f1-4298-8d99-c767c6125baddbea0862-3f10-4545-8226-16d8855e9802","ab756392-9f06-4b51-b5a7-e2bc054e3c64f4b2e52e-ae98-4529-ba4a-c6d452d80feb","2b401c5e-2fe8-42bc-b1e7-0081a3ed09f1f79ca605-2e12-43af-846c-3eacc6717426","5e800657-43be-4417-b06d-886eeadd97a5b4352d7c-a9f6-4fad-a97e-e64546416132","0747a794-7018-49e9-9339-11e3ba73d9b981fdd374-2337-4980-a3fe-227e6034fe54","26b37bae-5a0c-432c-9874-5741a8585d894bc58b0a-dc97-4610-a083-b6ae385b3df1","8b96f56b-4590-49e1-ad4e-fb6fff4ec8505679d937-3241-447a-b5ea-5090beae8c59","9bb64e8d-1c01-4be2-9c48-dd016c1e979bbee81494-ffcb-48d1-a934-cf6d6828feea","4267e7d1-bb45-49d1-894c-311d14b8143131bf43fe-fadf-49bf-b6be-7dc30d36ee2a","0a92a3bf-031e-44a9-a1fb-ae6cce4868df136a229c-3a64-4ecf-adf9-c3467fa4e41e","bf2bc7d5-dbc2-449a-ba28-c112be57b802b9ce2480-1d1b-44fc-8519-8154116f4dcd","f8bce9f9-8674-47d8-9964-3884c77caa4eadb41ee3-708b-4a40-b8f7-e07e8f0bcb38","8a71cffb-2502-492c-8c93-586f610abf3487653840-b3ce-4de9-b154-dd60968799da","845ea8b9-d109-4839-80cf-aba20350e752d3d399cf-d600-4625-968d-364707bf9604","8c3441a1-5993-477d-85f2-953f7f32ef2889165d54-b1a8-4960-8c98-1eca31186350","d07c3f6e-2cf7-4bbd-8ed5-059cc13bc365134c167f-dd8e-4198-955b-9d69808829e0","7a7ce941-2e4b-46f6-bc24-5188de179160ccbf002c-cc54-4b4a-8c25-fee60814a98a","2cb23646-3c77-4d1b-9091-bb2453733f5e78527814-1deb-49be-a290-7276774174fc","0b4226b5-02f1-4f8a-9273-8e9c36bb999c236f52ba-720a-48bc-94be-0053371e241f","ce81132d-bd65-44d8-88d4-cb38b98bf05e90acc3b0-79f6-4778-a3d6-ce22bfa2eead","9aa25ff8-0600-4dd5-84d7-4c21a1c83b3d617762e4-cb9e-4695-be4e-c905f2c7136d","9c92d49d-bafc-4e6b-94a3-030a10696fb0d4c50ec6-6040-4578-8fa9-d175633ca03f","e55cde4f-1b9a-404e-b951-df52eb23bae7dd803a93-943d-429b-a954-5947fe2e29ab","d57f3008-9eb9-449f-b43b-9154c708ae1b8ce31f2f-d620-4916-b51e-3477ec724400","6d3ed46a-0f09-4c0e-98ee-972e636b8e943b572b64-58bd-42c6-b7c3-ab5dbed23b43","30d3c1a8-6572-4e25-9979-b8a7f9658c48d50811b9-65e9-4015-a07e-d577283aaf9a","6542fd13-6fab-4d2e-b320-c06073d25bb156d13ee4-ffbd-4eb4-b660-49113a76e064","d73135a5-2f54-4350-81ab-b5e60817e681b55c69ef-57de-4e7c-894f-baf82320de26","fe002cfe-f175-449f-80ed-165d132712a951c8ce0e-6156-4774-8b94-e96bb33ae479"};
        List<String> eipList = Arrays.asList(eipTodo);
        List<String> hrList = Arrays.asList(hrTodo);

        for(String e:eipList){

            if(hrList.contains(e)){
                System.out.println(e);
            }
        }*/




        CurrentLoginUser user = new CurrentLoginUser("admin", "admin", "系统管理员", "A","","", "A02","","","","","","","241");
        String jwtToken = TokenUtils.createSimpleToken(user);
        System.out.println(jwtToken);
/*
        // 获取前一天的日期
        java.util.Date yesterday = DateUtil.yesterday();
        // 打印结果
        System.out.println("昨天的日期是：" + DateUtil.formatDate( DateUtil.yesterday()));

        System.out.println(DateUtil.formatDate(DateUtil.offsetDay(DateUtil.parseDate("2024-03-11"), 1)));*/

    }


    /**
     * 重要指标-列表
     * @param pageReqDTO 分页参数
     * @return 重要指标列表
     */
    @GetMapping("/list")
    @ApiOperation(value = "重要指标-列表")
    public PageResponse<IndicatorResDTO> page(@RequestParam String dataType,
                                              @RequestParam(required = false) String startDate,
                                              @RequestParam(required = false) String endDate,
                                                    @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(indicatorService.list(dataType,startDate,endDate,pageReqDTO));
    }

    /**
     * 重要指标-详情
     * @param id 入参数
     * @return 成功
     */
    @GetMapping("/detail")
    @ApiOperation(value = "重要指标")
    public DataResponse<IndicatorResDTO> add(@RequestParam String id) {
        return DataResponse.of(indicatorService.detail(id));
    }

    /**
     * 重要指标-新增
     * @param indicatorReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/add")
    @ApiOperation(value = "重要指标-新增")
    public DataResponse<T> add(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.create.class})
                               @RequestBody IndicatorReqDTO indicatorReqDTO) {
        indicatorService.add(currentLoginUser,indicatorReqDTO);
        return DataResponse.success();
    }

    /**
     * 重要指标-编辑
     * @param indicatorReqDTO 入参数
     * @return 成功
     */
    @PostMapping("/modify")
    @ApiOperation(value = "重要指标-编辑")
    public DataResponse<T> modify(@CurrUser CurrentLoginUser currentLoginUser,
                               @Validated({ValidationGroup.modify.class})
                               @RequestBody IndicatorReqDTO indicatorReqDTO) {
        indicatorService.modify(currentLoginUser,indicatorReqDTO);
        return DataResponse.success();
    }

    /**
     * 重要指标-删除
     * @param baseIdsEntity 入参数
     * @return 成功
     */
    @PostMapping("/delete")
    @ApiOperation(value = "重要指标-删除)")
    public DataResponse<T> delete(@RequestBody  BaseIdsEntity baseIdsEntity) {
        indicatorService.delete(baseIdsEntity.getIds());
        return DataResponse.success();
    }

}
