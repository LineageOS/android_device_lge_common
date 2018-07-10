package com.lge.telephony.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class TelephonyProxy {
    private static final String TAG = "TelephonyProxy";

    public static final class Carriers {
        public static final int CARRIER_HFA_EDITED = 11;
        public static final Uri CONTENT_ADMIN_URI = Uri.parse("content://telephony/carriers/admin_status");
        public static final Uri CONTENT_BACKUP_OTA_URI = Uri.parse("content://telephony/carriers/backup_ota_apn");
        public static final Uri CONTENT_BACKUP_URI = Uri.parse("content://telephony/carriers/backup_apn");
        public static final String DEFAULTSETTING = "defaultsetting";
        public static final int DEFAULTSETTING_EDITABLE = 0;
        public static final int DEFAULTSETTING_HIDDEN = 2;
        public static final int DEFAULTSETTING_NOT_EDITABLE = 1;
        public static final String INACTIVETIMER = "inactivetimer";
        public static final String OTA_APN_KEY_TYPE = "OtaApnKeyType";
        public static final String OTA_APN_KEY_VALUE = "OtaApnKeyValue";
        public static final String PCSCF = "pcscf";
        public static final int PCSCF_DISABLED = 0;
        public static final int PCSCF_ENABLED = 1;
        public static final String PPP_DIALING_NUMBER = "ppp_dialing_number";
        public static final String PPP_DIALING_NUMBER_DEFAULT = "0";
        public static final String SIM_SUBSCRIPTION = "sim_subscription";
        public static final int SUBSCRIPTION_NONE = -1;
        public static final int SUBSCRIPTION_SIM1 = 0;
        public static final int SUBSCRIPTION_SIM2 = 1;
        public static final int SUBSCRIPTION_SIM3 = 2;
        public static final int SUBSCRIPTION_SIM4 = 3;
        public static final String TOUCH = "touch";
        public static final int TOUCH_NONE = 0;
        public static final String USERCREATESETTING = "usercreatesetting";
        public static final int USERCREATESETTING_MANUAL = 1;
        public static final int USERCREATESETTING_OTA = 2;
        public static final int USERCREATESETTING_PRELOADED = 0;
    }

    public static final class Chameleon implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://telephony/chameleon");
        public static final String EPDG_ADDR = "epdg_addr";
        public static final String ROVEIN_PER = "rovein_per";
        public static final String ROVEIN_RSSI = "rovein_rssi";
        public static final String ROVEIN_SNR = "rovein_snr";
        public static final String ROVEOUT_PER = "roveout_per";
        public static final String ROVEOUT_RSSI = "roveout_rssi";
        public static final String ROVEOUT_SNR = "roveout_snr";
    }

    public static final class Ifom implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.parse("content://telephony/ifom");

        private Ifom() {
        }
    }

    public static final class Ike implements BaseColumns {
        public static final String CERT_REQ_ENABED = "cert_req_enabled";
        public static final String CONFIGURED_IKE_PORT = "configured_ike_port";
        public static final Uri CONTENT_URI = Uri.parse("content://telephony/ike");
        public static final String EPC_HANDOFF_RETRY_COUNT = "epc_handoff_retry_count";
        public static final String EPDG_CALL_SETUP_TIMER_SEC = "epdg_call_setup_timer_sec";
        public static final String EPDG_FQDN = "epdg_fqdn";
        public static final String EPDG_FQDN_VALIDATION_ENABLED = "epdg_fqdn_validation_enabled";
        public static final String EPDG_IPV4_ADDRESS = "epdg_ipv4_address";
        public static final String EPDG_PLMN_LIST = "epdg_plmn_list";
        public static final String EPDG_TUNNEL_CONFIG_TIMER_SEC = "epdg_tunnel_config_timer_sec";
        public static final String ESP_AES_CBC_ENCR_KEY_SIZE_LIST = "esp_aes_cbc_encr_key_size_list";
        public static final String ESP_AES_CTR_ENCR_KEY_SIZE_LIST = "esp_aes_ctr_encr_key_size_list";
        public static final String ESP_AUTH_ALGO_LIST = "esp_auth_algo_list";
        public static final String ESP_DH_GROUP_LIST = "esp_dh_group_list";
        public static final String ESP_ENCR_ALGO_LIST = "esp_encr_algo_list";
        public static final String ESP_MAX_TUNNEL_TIMER_SEC = "esp_max_tunnel_timer_sec";
        public static final String ESP_REKEY_TIMER_HARD_SEC = "esp_rekey_timer_hard_sec";
        public static final String ESP_REKEY_TIMER_SOFT_SEC = "esp_rekey_timer_soft_sec";
        public static final String IKEV2_AES_CBC_ENCR_KEY_SIZE_LIST = "ikev2_aes_cbc_encr_key_size_list";
        public static final String IKEV2_AES_CTR_ENCR_KEY_SIZE_LIST = "ikev2_aes_ctr_encr_key_size_list";
        public static final String IKEV2_DH_GROUP_LIST = "ikev2_dh_group_list";
        public static final String IKEV2_DPD_TIMER = "ikev2_dpd_timer_sec";
        public static final String IKEV2_EAP_FRA_PSEUDO_ENABLED = "ikev2_eap_fra_pseudo_enabled";
        public static final String IKEV2_EAP_REAUTH_REALM_ENABLED = "ikev2_eap_reauth_realm_enabled";
        public static final String IKEV2_ENCR_ALGO_LIST = "ikev2_encr_algo_list";
        public static final String IKEV2_HASH_ALGO_LIST = "ikev2_hash_algo_list";
        public static final String IKEV2_MAX_RETRIES = "ikev2_max_retries";
        public static final String IKEV2_PEER_ID_TYPE = "ikev2_peer_id_type";
        public static final String IKEV2_PRF_ALGO_LIST = "ikev2_prf_algo_list";
        public static final String IKEV2_RETRANSMIT_TIMER_SEC = "ikev2_retransmit_timer_sec";
        public static final String IKEV2_SA_REKEY_TIMER_HARD_SEC = "ikev2_sa_rekey_timer_hard_sec";
        public static final String IKEV2_SA_REKEY_TIMER_SOFT_SEC = "ikev2_sa_rekey_timer_soft_sec";
        public static final String IKEV2_SELF_ID_TYPE = "ikev2_self_id_type";
        public static final String IMEI_CP_ATTR_TYPE_VAL = "imei_cp_attr_type_val";
        public static final String KE_PAYLOAD_ENABLED = "ke_payload_enabled";
        public static final String MIN_EPDG_ADDR_DNS_TTL_VALUE = "min_epdg_addr_dns_ttl_value";
        public static final String NATT_ENABLED = "natt_enabled";
        public static final String NATT_KEEP_ALIVE_TIMER_SEC = "natt_keep_alive_timer_sec";
        public static final String PCSCFV4_ATTR_TYPE_VAL = "pcscfv4_attr_type_val";
        public static final String PCSCFV6_ATTR_TYPE_VAL = "pcscfv6_attr_type_val";
        public static final String PCSCFV6_WITH_PREFIX_LENGTH = "pcscfv6_with_prefix_len";
        public static final String PDN_THROTTLE_SERIES_1_CAUSE_CODES = "pdn_throttling_series_1_cause_codes";
        public static final String PDN_THROTTLE_SERIES_1_VALUES = "pdn_throttling_series_1_values";
        public static final String PDN_THROTTLE_SERIES_2_CAUSE_CODES = "pdn_throttling_series_2_cause_codes";
        public static final String PDN_THROTTLE_SERIES_2_VALUES = "pdn_throttling_series_2_values";
        public static final String PDN_THROTTLE_SERIES_3_CAUSE_CODES = "pdn_throttling_series_3_cause_codes";
        public static final String PDN_THROTTLE_SERIES_3_VALUES = "pdn_throttling_series_3_values";
        public static final String PDN_THROTTLE_SERIES_DEFAULT_VALUES = "pdn_throttling_series_default_values";
        public static final String PDN_THROTTLE_TIMER_VALUES_SEC = "pdn_throttle_timer_values_sec";
        public static final String STATIC_FQDN_ENABLED = "static_fqdn_enabled";
        public static final String WLAN_OFFLOAD_MTU_SIZE = "wlan_offload_mtu_size";
        public static final String WLAN_OFFLOAD_THROTTLE_TIMER = "wlan_offload_throttle_timer";

        private Ike() {
        }
    }

    public static final class Mapcon implements BaseColumns {
        public static final String ACCESS_NETWORK = "access_network";
        public static final String ALWAYS_ENABLED = "always_enabled";
        public static final Uri CONTENT_URI = Uri.parse("content://telephony/mapcon");
        public static final String FALLBACK_MODE = "fallback_mode";
        public static final String GUARD_TIME = "guard_time";
        public static final String HO_DECISION = "ho_decision";
        public static final String HO_MEASURE = "ho_measure";
        public static final String HO_POLICY = "ho_policy";
        public static final String HO_POLICY_ROAM = "ho_policy_roam";
        public static final String NO_AVAILABLE_TIME = "no_available_time";

        private Mapcon() {
        }
    }
}
